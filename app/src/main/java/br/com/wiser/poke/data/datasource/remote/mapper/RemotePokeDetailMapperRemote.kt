package br.com.wiser.poke.data.datasource.remote.mapper

import br.com.wiser.poke.data.datasource.remote.model.ResPkmnDetail
import br.com.wiser.poke.data.model.PokeDetailModel
import br.com.wiser.poke.data.model.StatusModel

class RemotePokeDetailMapperRemote : RemoteResponseMapper<ResPkmnDetail, PokeDetailModel> {

    override fun map(response: ResPkmnDetail): PokeDetailModel {
        val types = response.types.map { it.type.name }
        val stats = response.stats.map { StatusModel(it.stat.name, it.base_stat) }
        return PokeDetailModel(response.id, response.name, response.height.toFloat(), response.weight.toFloat(), stats, types)
    }
}