package br.com.wiser.poke.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_page")
class RemotePageTable(
        @PrimaryKey
        val listName: String,
        val nextPage: Int?
)