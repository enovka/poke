package br.com.wiser.poke.data.domain.entity

data class PokeDetailEntity(
        val id: Int,
        val name: String,
        val height: Float,
        val weight: Float,
        val sprite: String?,
        val stats: List<StatEntity>,
        val types: List<String>
)

data class StatEntity(
        val value: Int,
        val name: String
)