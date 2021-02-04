package br.com.wiser.poke.data.mapper

import br.com.wiser.poke.data.domain.entity.PokeDetailEntity
import br.com.wiser.poke.data.model.PokeDetailModel

class DetailModelMapper(private val spriteMapper: SpriteMapper, private val statusMapper: StatusModelMapper) :
    ModelMapper<PokeDetailModel, PokeDetailEntity> {

    override fun map(model: PokeDetailModel): PokeDetailEntity {
        return PokeDetailEntity(
                model.id,
                model.name,
                model.height * 10F,
                model.weight / 10F,
                spriteMapper.map(model.id),
                model.statuses.map { statusMapper.map(it) },
                model.types
        )
    }
}