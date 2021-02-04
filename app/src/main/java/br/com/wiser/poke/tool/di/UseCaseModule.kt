package br.com.wiser.poke.tool.di

import br.com.wiser.poke.uc.IUCPokeDetail
import br.com.wiser.poke.uc.IUCPokeListPage
import br.com.wiser.poke.uc.IUCPokeSpecie
import br.com.wiser.poke.uc.implementation.UCPokeDetailImpl
import br.com.wiser.poke.uc.implementation.UCPokeListPageImpl
import br.com.wiser.poke.uc.implementation.UCPokeSpecieImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<IUCPokeListPage> { UCPokeListPageImpl(repository = get()) }
    factory<IUCPokeDetail> { UCPokeDetailImpl(repository = get()) }
    factory<IUCPokeSpecie> { UCPokeSpecieImpl(repository = get()) }
}