package br.com.wiser.poke.tool.di

import br.com.wiser.poke.data.datasource.ILocalIPokeDataSource
import br.com.wiser.poke.data.datasource.INetworkIPokeDataSource
import br.com.wiser.poke.data.datasource.local.LocalIPokeDataSourceImpl
import br.com.wiser.poke.data.datasource.remote.NetworkIPokeDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<INetworkIPokeDataSource> { NetworkIPokeDataSourceImpl(api = get()) }
    single<ILocalIPokeDataSource> { LocalIPokeDataSourceImpl(dao = get()) }
}