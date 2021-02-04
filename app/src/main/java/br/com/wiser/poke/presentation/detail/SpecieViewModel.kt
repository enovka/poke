package br.com.wiser.poke.presentation.detail

import br.com.wiser.poke.uc.IUCPokeSpecie
import io.uniflow.androidx.flow.AndroidDataFlow

class SpecieViewModel(private val getSpecies: IUCPokeSpecie) : AndroidDataFlow() {

    private var lastSpeciesName = ""

    init {
        action { setState(VarietyState.Loading) }
    }

    fun reload() {
        load(lastSpeciesName)
    }

    fun load(name: String) {
        if (name != lastSpeciesName) {
            action(
                    onAction = {
                        setState(SpecieState.Loading)
                        val species = getSpecies.load(name)
                        species.varieties.find { it.isDefault ?: false }?.pokemon?.name?.let {
                            sendEvent(SpecieEvent.SpeciesLoaded(it))
                        }
                        setState(SpecieState.Loaded(species))
                        lastSpeciesName = name
                    },
                    onError = { error, _ ->
                        setState(SpecieState.Error(error, name))
                    }
            )
        }
    }

}