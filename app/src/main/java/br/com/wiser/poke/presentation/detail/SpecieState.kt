package br.com.wiser.poke.presentation.detail

import br.com.wiser.poke.data.domain.entity.PokeSpecieEntity
import io.uniflow.core.flow.data.UIState

open class SpecieState : UIState() {
    object Loading : SpecieState()
    data class Loaded(val species: PokeSpecieEntity) : SpecieState()
    data class Error(val error: Throwable, val name: String) : SpecieState()
}