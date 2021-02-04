package br.com.wiser.poke.data.datasource.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "species")
class SpeciesElementTable(
        @PrimaryKey
        val name: String,
        val url: String?
)

@Entity(tableName = "species_data")
class SpeciesDataTable(
        @PrimaryKey
        val name: String,
        val id: Int,
        val description: String,
        val evolutionChain: String?
)

@Entity(tableName = "species_variety", primaryKeys = ["pokemonName", "speciesName"])
data class SpeciesVarietyTable(
        val pokemonName: String,
        val speciesName: String,
        val isDefault: Boolean
)

@Entity(tableName = "pokemon")
data class PokeTable(
        @PrimaryKey val name: String = "",
        val url: String?
)

data class SpeciesVariety(
        @Embedded val variety: SpeciesVarietyTable,
        @Relation(parentColumn = "pokemonName", entityColumn = "name")
        val pokemon: PokeTable
)

data class Species(
        @Embedded val speciesDataTable: SpeciesDataTable,
        @Relation(parentColumn = "name", entityColumn = "speciesName", entity = SpeciesVarietyTable::class)
        val varieties: List<SpeciesVariety>?
)

