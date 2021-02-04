package br.com.wiser.poke.presentation.list

import io.uniflow.core.flow.data.UIEvent

sealed class PokeListEvent : UIEvent() {
    object QueryChanged : PokeListEvent()
    object RefreshContent : PokeListEvent()
}