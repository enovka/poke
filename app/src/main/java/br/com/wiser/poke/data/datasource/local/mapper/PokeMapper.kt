package br.com.wiser.poke.data.datasource.local.mapper

import br.com.wiser.poke.data.datasource.local.model.PokeTable
import br.com.wiser.poke.data.model.PokemonModel

class PokeMapper : FromDatabaseMapper<PokeTable, PokemonModel> {
    override fun fromDatabase(entity: PokeTable): PokemonModel {
        return PokemonModel(entity.name, entity.url)
    }
}