package br.com.wiser.poke.data.datasource.local.mapper

import br.com.wiser.poke.data.datasource.local.model.PokeDetail
import br.com.wiser.poke.data.datasource.local.model.PokeDetailTable
import br.com.wiser.poke.data.datasource.local.model.PokeStatTable
import br.com.wiser.poke.data.datasource.local.model.PokeTypeTable
import br.com.wiser.poke.data.model.PokeDetailModel
import br.com.wiser.poke.data.model.StatusModel

class PokeDetailMapper : IDatabaseMapper<PokeDetail, PokeDetailModel> {
    override fun fromDatabase(entity: PokeDetail): PokeDetailModel {
        val stats = entity.stat.map { StatusModel(it.statName, it.value) }
        val type = entity.pokeType.map { it.typeName }
        return PokeDetailModel(
            entity.detail.id,
            entity.detail.name,
            entity.detail.height,
            entity.detail.weight,
                stats,
                type
        )
    }

    override fun toDatabase(model: PokeDetailModel): PokeDetail {
        val stats = model.statuses.map { PokeStatTable(model.name, it.name, it.value) }
        val type = model.types.map { PokeTypeTable(model.name, it) }
        val detail = PokeDetailTable(
                model.name,
                model.id,
                model.height,
                model.weight,
        )
        return PokeDetail(detail, stats, type)
    }
}