package br.com.wiser.poke.data.datasource.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "detail")
data class PokeDetailTable(
        @PrimaryKey val name: String,
        val id: Int,
        val height: Float,
        val weight: Float
)

@Entity(tableName = "poke_stat", primaryKeys = ["pokemonName", "statName"])
data class PokeStatTable(
        val pokemonName: String,
        val statName: String,
        val value: Int
)

@Entity(tableName = "poke_type", primaryKeys = ["pokemonName", "typeName"])
data class PokeTypeTable(
        val pokemonName: String,
        val typeName: String,
)

data class PokeDetail(
        @Embedded val detail: PokeDetailTable,
        @Relation(parentColumn = "name", entityColumn = "pokemonName")
        val stat: List<PokeStatTable>,
        @Relation(parentColumn = "name", entityColumn = "pokemonName")
        val pokeType: List<PokeTypeTable>
)

