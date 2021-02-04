package br.com.wiser.poke.data.domain.entity

data class PokeListPageEntity(
        val next: Int?,
        val previous: Int?,
        val results: List<PokeEntity>
)
