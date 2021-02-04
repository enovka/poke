package br.com.wiser.poke.presentation.di

import br.com.wiser.poke.presentation.detail.SpecieViewModel
import br.com.wiser.poke.presentation.detail.VarietyViewModel
import br.com.wiser.poke.presentation.list.PokeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokeListViewModel(get()) }
    viewModel { SpecieViewModel(get()) }
    viewModel { VarietyViewModel(get()) }
}