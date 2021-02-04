package br.com.wiser.poke.presentation.detail

import io.uniflow.core.flow.data.UIEvent

sealed class SpecieEvent : UIEvent() {
    data class SpeciesLoaded(val defaultVariety: String) : SpecieEvent()
}