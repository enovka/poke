package br.com.wiser.poke.data.mapper

import br.com.wiser.poke.data.domain.entity.PokeSpecieEntity
import br.com.wiser.poke.data.domain.entity.PokeVarietyEntity
import br.com.wiser.poke.data.model.SpecieModel


class SpecieModelMapper(private val customMapper: CustomModelMapper) :
    ModelMapper<SpecieModel, PokeSpecieEntity> {

    override fun map(model: SpecieModel): PokeSpecieEntity {
        val varieties = model.varieties.map { PokeVarietyEntity(customMapper.map(it.pokemon), it.isDefault) }
        return PokeSpecieEntity(model.id, model.name, model.description.replace("\n"," "), varieties, model.evolutionChain)
    }
}