package br.com.wiser.poke.data.mapper

import br.com.wiser.poke.data.domain.entity.PokeListPageEntity
import br.com.wiser.poke.data.model.ListModel

class ListModelMapper(private val customMapper: CustomModelMapper) :
    ModelMapper<ListModel, PokeListPageEntity> {
    override fun map(model: ListModel): PokeListPageEntity {
        return PokeListPageEntity(model.next, model.previous, model.results.map { pkmn -> customMapper.map(pkmn) })
    }
}