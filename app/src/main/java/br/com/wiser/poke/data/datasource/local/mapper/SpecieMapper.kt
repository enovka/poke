package br.com.wiser.poke.data.datasource.local.mapper

import br.com.wiser.poke.data.datasource.local.model.*
import br.com.wiser.poke.data.model.SpecieModel
import br.com.wiser.poke.data.model.VarietyModel

class SpecieMapper : IDatabaseMapper<Species, SpecieModel> {
    override fun fromDatabase(entity: Species): SpecieModel {
        val pokeMapper = PokeMapper()
        val variete = entity.varieties?.map { VarietyModel(pokeMapper.fromDatabase(it.pokemon), it.variety.isDefault) }
        return SpecieModel(entity.speciesDataTable.id, entity.speciesDataTable.name, entity.speciesDataTable.description, variete.orEmpty(), entity.speciesDataTable.evolutionChain)
    }

    override fun toDatabase(model: SpecieModel): Species {
        val variete = model.varieties.map {
            SpeciesVariety(
                    SpeciesVarietyTable(it.pokemon.name, model.name, it.isDefault ?: false),
                    PokeTable(it.pokemon.name, it.pokemon.url)
            )
        }
        return Species(
                SpeciesDataTable(model.name, model.id, model.description, model.evolutionChain),
                variete)
    }
}