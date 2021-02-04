package br.com.wiser.poke.data.mapper

import br.com.wiser.poke.data.domain.entity.StatEntity
import br.com.wiser.poke.data.model.StatusModel

class StatusModelMapper : ModelMapper<StatusModel, StatEntity> {
    override fun map(model: StatusModel): StatEntity {
        return StatEntity(model.value, model.name)
    }
}