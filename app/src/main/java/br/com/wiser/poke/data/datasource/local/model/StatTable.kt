package br.com.wiser.poke.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat")
data class StatTable(@PrimaryKey val name: String)