package br.com.wiser.poke.data.datasource

import br.com.wiser.poke.data.model.*

interface IPokeDataSource {
    suspend fun list(query: String?, pageSize: Int, page: Int) : ListModel?
    suspend fun detail(name: String) : PokeDetailModel?
    suspend fun species(name: String) : SpecieModel?
}

interface INetworkIPokeDataSource : IPokeDataSource

interface ILocalIPokeDataSource : IPokeDataSource {

    suspend fun getNextRemotePageKey(pageKey: String) : RemotePage?

    suspend fun insertPokemon(pokemon: List<PokemonModel>)
    suspend fun insertDetail(detail: PokeDetailModel)
    suspend fun insertSpecies(species: SpecieModel)
    suspend fun insertNextRemotePageKey(page: RemotePage)

    suspend fun clearData()

}