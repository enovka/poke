package br.com.wiser.poke.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.wiser.poke.data.datasource.local.model.*

@Database(entities = [
    StatTable::class,
    TypeTable::class,
    SpeciesElementTable::class,
    SpeciesDataTable::class,
    PokeTable::class,
    SpeciesVarietyTable::class,
    PokeDetailTable::class,
    PokeStatTable::class,
    PokeTypeTable::class,
    RemotePageTable::class
], version = 12)

abstract class PokeDatabase : RoomDatabase() {
    abstract fun pokeDao(): IPokeDao
}