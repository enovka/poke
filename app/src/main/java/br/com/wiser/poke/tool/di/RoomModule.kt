package br.com.wiser.poke.tool.di

import androidx.room.Room
import br.com.wiser.poke.data.datasource.local.PokeDatabase
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(), PokeDatabase::class.java, "poke_database")
                .fallbackToDestructiveMigration()
                .build()
    }

    single { get<PokeDatabase>().pokeDao() }
}