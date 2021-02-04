package br.com.wiser.poke.data.datasource.remote

import br.com.wiser.poke.data.datasource.INetworkIPokeDataSource
import br.com.wiser.poke.data.datasource.remote.mapper.RemotePokeDetailMapperRemote
import br.com.wiser.poke.data.datasource.remote.mapper.RemotePokeMapperRemote
import br.com.wiser.poke.data.datasource.remote.mapper.RemotePokeSpecieMapperRemote
import br.com.wiser.poke.data.model.ListModel
import br.com.wiser.poke.data.model.PokeDetailModel
import br.com.wiser.poke.data.model.SpecieModel
import java.io.IOException

class NetworkIPokeDataSourceImpl(val api: PokeApi) : INetworkIPokeDataSource {
    override suspend fun list(query: String?, pageSize: Int, page: Int): ListModel {
        val offset = page * pageSize
        val response = wrapExecution { api.list(pageSize, offset) }
        val pokeMapper = RemotePokeMapperRemote()
        return ListModel(response.next?.let { page + 1 }, response.previous?.let { page - 1 }, response.results.map { pokeMapper.map(it) })
    }

    override suspend fun detail(name: String): PokeDetailModel {
        return wrapExecution { RemotePokeDetailMapperRemote().map(api.detail(name)) }
    }

    override suspend fun species(name: String): SpecieModel {
        return wrapExecution { RemotePokeSpecieMapperRemote().map(api.species(name)) }
    }

    @Throws(Throwable::class)
    private suspend fun <T> wrapExecution(exec: suspend () -> T): T {
        try {
           return exec.invoke()
        } catch (ex: IOException) {
            throw RemotekException(ex)
        }
    }
}