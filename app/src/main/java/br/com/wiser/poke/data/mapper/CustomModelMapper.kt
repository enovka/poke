package br.com.wiser.poke.data.mapper

import br.com.wiser.poke.data.domain.entity.PokeEntity
import br.com.wiser.poke.data.model.PokemonModel

class CustomModelMapper(private val urlIdMapper: UrlIdMapper, private val spriteMapper: SpriteMapper) :
    ModelMapper<PokemonModel, PokeEntity> {
    override fun map(model: PokemonModel): PokeEntity {
        return PokeEntity(model.name, model.url, urlIdMapper.map(model.url), spriteMapper.map(model.url))
    }
}