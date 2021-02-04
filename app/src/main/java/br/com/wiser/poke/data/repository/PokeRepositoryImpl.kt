package br.com.wiser.poke.data.repository

import br.com.wiser.poke.data.datasource.ILocalIPokeDataSource
import br.com.wiser.poke.data.datasource.INetworkIPokeDataSource
import br.com.wiser.poke.data.domain.entity.PokeDetailEntity
import br.com.wiser.poke.data.domain.entity.PokeListPageEntity
import br.com.wiser.poke.data.domain.entity.PokeSpecieEntity
import br.com.wiser.poke.data.mapper.*
import br.com.wiser.poke.data.model.RemotePage

class PokeRepositoryImpl(
    private val networkDataSource: INetworkIPokeDataSource,
    private val localDataSource: ILocalIPokeDataSource
) : IPokeRepository {


    override suspend fun getList(query: String?, page: Int, pageSize: Int, refresh: Boolean): PokeListPageEntity {
        if (refresh && page == 0) {
            localDataSource.clearData()
        }
        return getData(query, page, pageSize, false)
    }

    private suspend fun getData(query: String?, page: Int, pageSize: Int, applyExitCondition: Boolean): PokeListPageEntity {
        var localPage = loadListFromDatabase(query, page, pageSize)
        if (localPage.next == null) {
            val nextRemoteKey = localDataSource.getNextRemotePageKey(REMOTE_SPECIES_LIST)
            if (nextRemoteKey?.nextPage != null || (page == 0 && !applyExitCondition)) {
                updateListFromRemote(query, nextRemoteKey?.nextPage ?: 0, REMOTE_SIZE)
                localPage = getData(query, page, pageSize, true)
            }
        }
        return localPage
    }


    override suspend fun getDetailByName(name: String): PokeDetailEntity {
        return loadDetailFromDatabaseByName(name) ?: loadDetailFromRemoteByName(name)!!
    }

    override suspend fun getSpeciesByName(name: String): PokeSpecieEntity {
        return loadSpeciesFromDatabaseByName(name) ?: loadSpeciesFromRemoteByName(name)!!
    }

    private suspend fun loadListFromDatabase(query: String?, page: Int, pageSize: Int): PokeListPageEntity {
        return localDataSource.list(query, pageSize, page)?.let {
            return ListModelMapper(CustomModelMapper(UrlIdMapper(), SpriteMapper())).map(it)
        } ?: PokeListPageEntity(null, null, emptyList())
    }

    private suspend fun loadSpeciesFromDatabaseByName(name: String): PokeSpecieEntity? {
        return localDataSource.species(name)?.let {
            SpecieModelMapper(CustomModelMapper(UrlIdMapper(), SpriteMapper())).map(it)
        }
    }

    private suspend fun loadDetailFromDatabaseByName(name: String): PokeDetailEntity? {
        return localDataSource.detail(name)?.let {
            DetailModelMapper(SpriteMapper(), StatusModelMapper()).map(it)
        }
    }

    private suspend fun loadDetailFromRemoteByName(name: String): PokeDetailEntity? {
        return networkDataSource.detail(name)?.let {
            localDataSource.insertDetail(it)
            return DetailModelMapper(SpriteMapper(), StatusModelMapper()).map(it)
        }
    }

    private suspend fun updateListFromRemote(query: String?, page: Int, pageSize: Int): PokeListPageEntity {
        return networkDataSource.list(query, pageSize, page)?.let {
            localDataSource.insertPokemon(it.results)
            localDataSource.insertNextRemotePageKey(RemotePage(REMOTE_SPECIES_LIST, it.next))
            return ListModelMapper(CustomModelMapper(UrlIdMapper(), SpriteMapper())).map(it)
        } ?: PokeListPageEntity(null, null, emptyList())
    }

    private suspend fun loadSpeciesFromRemoteByName(name: String): PokeSpecieEntity? {
        return networkDataSource.species(name)?.let {
            localDataSource.insertSpecies(it)
            return SpecieModelMapper(CustomModelMapper(UrlIdMapper(), SpriteMapper())).map(it)
        }
    }

    companion object {
        const val REMOTE_SIZE = 20
        const val REMOTE_SPECIES_LIST = "species_list"
    }


}