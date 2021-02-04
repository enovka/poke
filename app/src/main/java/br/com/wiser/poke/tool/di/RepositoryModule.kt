package br.com.wiser.poke.tool.di

import br.com.wiser.poke.data.repository.IPokeRepository
import br.com.wiser.poke.data.repository.PokeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<IPokeRepository> { PokeRepositoryImpl(networkDataSource = get(), localDataSource = get()) }
}