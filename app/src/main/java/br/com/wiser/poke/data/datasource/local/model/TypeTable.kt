package br.com.wiser.poke.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeTable(@PrimaryKey val name: String)