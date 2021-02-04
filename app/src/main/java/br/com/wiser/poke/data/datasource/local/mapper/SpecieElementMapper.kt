package br.com.wiser.poke.data.datasource.local.mapper

import br.com.wiser.poke.data.datasource.local.model.SpeciesElementTable
import br.com.wiser.poke.data.model.PokemonModel

class SpecieElementMapper : IDatabaseMapper<SpeciesElementTable, PokemonModel> {
    override fun fromDatabase(entity: SpeciesElementTable): PokemonModel {
        return PokemonModel(entity.name, entity.url)
    }

    override fun toDatabase(model: PokemonModel): SpeciesElementTable {
        return SpeciesElementTable(model.name, model.url)
    }

}