package br.com.wiser.poke.presentation.detail

import br.com.wiser.poke.uc.IUCPokeDetail
import io.uniflow.androidx.flow.AndroidDataFlow

class VarietyViewModel(private val getDetail: IUCPokeDetail) : AndroidDataFlow() {

    private var lastDetailName = ""

    init {
        action { setState(VarietyState.Loading) }
    }

    fun reload() {
        load(lastDetailName)
    }

    fun load(name: String) {
        if(name != lastDetailName) {
            action(
                    onAction = {
                        setState(VarietyState.Loading)
                        setState(VarietyState.Loaded(getDetail.load(name)))
                        lastDetailName = name
                    },
                    onError = { error, _ ->
                        setState(VarietyState.Error(error, name))
                    }
            )
        }
    }
}