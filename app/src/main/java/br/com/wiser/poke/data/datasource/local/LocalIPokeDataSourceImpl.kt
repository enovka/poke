package br.com.wiser.poke.data.datasource.local

import br.com.wiser.poke.data.datasource.ILocalIPokeDataSource
import br.com.wiser.poke.data.datasource.local.mapper.PokeDetailMapper
import br.com.wiser.poke.data.datasource.local.mapper.RemotePageKeyMapper
import br.com.wiser.poke.data.datasource.local.mapper.SpecieElementMapper
import br.com.wiser.poke.data.datasource.local.mapper.SpecieMapper
import br.com.wiser.poke.data.datasource.local.model.StatTable
import br.com.wiser.poke.data.datasource.local.model.TypeTable
import br.com.wiser.poke.data.model.*

class LocalIPokeDataSourceImpl(val dao: IPokeDao) : ILocalIPokeDataSource {
    override suspend fun insertDetail(detail: PokeDetailModel) {
        val mapped = PokeDetailMapper().toDatabase(detail)
        dao.insertDetail(
                listOf(mapped.detail),
                mapped.stat.map { StatTable(it.statName) },
                mapped.stat,
                mapped.pokeType.map { TypeTable(it.typeName) },
                mapped.pokeType
        )
    }

    override suspend fun insertSpecies(species: SpecieModel) {
        val mapped = SpecieMapper().toDatabase(species)
        dao.insertSpecies(
                mapped.speciesDataTable,
                mapped.varieties.orEmpty().map { speciesVariety -> speciesVariety.pokemon },
                mapped.varieties.orEmpty().map { speciesVariety -> speciesVariety.variety })
    }

    override suspend fun insertPokemon(pokemon: List<PokemonModel>) {
        val mapper = SpecieElementMapper()
        dao.insertSpeciesElement(pokemon.map { mapper.toDatabase(it) })
    }

    override suspend fun clearData() {
        dao.deleteAll()
    }

    override suspend fun insertNextRemotePageKey(page: RemotePage) {
        dao.insertRemotePageKey(RemotePageKeyMapper().toDatabase(page))
    }

    override suspend fun getNextRemotePageKey(pageKey: String): RemotePage? {
        return dao.getRemotePageKey(pageKey)
    }

    override suspend fun list(query: String?, pageSize: Int, page: Int): ListModel? {
        val offset = page * pageSize
        val dbList = if (!query?.trim().isNullOrEmpty()) {
            dao.getPaginatedSpeciesByName(buildLikeParam(query!!), pageSize, offset)
        } else {
            dao.getPaginatedSpecies(pageSize, offset)
        }
        if (dbList.isNullOrEmpty()) {
            return null
        }
        val mapper = SpecieElementMapper()
        val mappedList = dbList.map { mapper.fromDatabase(it) }
        val next = if (mappedList.size == pageSize) {
            page + 1
        } else {
            null
        }
        val previous = if (page == 0) {
            null
        } else {
            page - 1
        }
        return ListModel(next, previous, mappedList)
    }

    override suspend fun detail(name: String): PokeDetailModel? {
        return dao.getDetailByName(name)?.let {
            PokeDetailMapper().fromDatabase(it)
        }
    }

    override suspend fun species(name: String): SpecieModel? {
        return dao.getSpeciesByName(name)?.let {
            SpecieMapper().fromDatabase(it)
        }
    }

    private fun buildLikeParam(query: String) = "%$query%"

}