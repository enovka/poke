package br.com.wiser.poke.data.datasource.remote

import br.com.wiser.poke.data.datasource.remote.model.PokeListDTO
import br.com.wiser.poke.data.datasource.remote.model.ResPkmnDetail
import br.com.wiser.poke.data.datasource.remote.model.ResPkmnSpecies
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET(LIST_ENDPOINT)
    @Headers("Content-type: application/json")
    suspend fun list(@Query(QUERY_LIMIT) limit: Int, @Query(QUERY_OFFSET) offset: Int) : PokeListDTO

    @GET(DETAIL_ENDPOINT)
    @Headers("Content-type: application/json")
    suspend fun detail(@Path(PATH_NAME) name: String) : ResPkmnDetail

    @GET(SPECIES_ENDPOINT)
    @Headers("Content-type: application/json")
    suspend fun species(@Path(PATH_NAME) name: String) : ResPkmnSpecies

    companion object {
        const val QUERY_LIMIT = "limit"
        const val QUERY_OFFSET = "offset"

        const val PATH_NAME = "name"

        const val LIST_ENDPOINT = "pokemon-species"
        const val DETAIL_ENDPOINT = "pokemon/{$PATH_NAME}"
        const val SPECIES_ENDPOINT = "pokemon-species/{$PATH_NAME}"
    }
}