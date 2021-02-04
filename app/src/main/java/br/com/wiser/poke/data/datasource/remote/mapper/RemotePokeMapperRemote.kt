package br.com.wiser.poke.data.datasource.remote.mapper

import br.com.wiser.poke.data.datasource.remote.model.PokeElementDTO
import br.com.wiser.poke.data.model.PokemonModel

class RemotePokeMapperRemote : RemoteResponseMapper<PokeElementDTO, PokemonModel> {
    override fun map(response: PokeElementDTO): PokemonModel {
        return PokemonModel(response.name, response.url)
    }
}