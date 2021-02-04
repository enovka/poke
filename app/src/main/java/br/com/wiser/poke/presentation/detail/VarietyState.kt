package br.com.wiser.poke.presentation.detail

import br.com.wiser.poke.data.domain.entity.PokeDetailEntity
import io.uniflow.core.flow.data.UIState

open class VarietyState : UIState() {
    object Loading : VarietyState()
    data class Loaded(val detail: PokeDetailEntity) : VarietyState()
    data class Error(val error: Throwable, val name: String) : VarietyState()
}