package br.com.wiser.poke.data.datasource.local.mapper

import br.com.wiser.poke.data.datasource.local.model.RemotePageTable
import br.com.wiser.poke.data.model.RemotePage

class RemotePageKeyMapper: IDatabaseMapper<RemotePageTable, RemotePage> {
    override fun fromDatabase(entity: RemotePageTable) = RemotePage(entity.listName, entity.nextPage)

    override fun toDatabase(model: RemotePage) = RemotePageTable(model.listName, model.nextPage)
}