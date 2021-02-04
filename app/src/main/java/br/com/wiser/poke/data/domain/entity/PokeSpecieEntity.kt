package br.com.wiser.poke.data.domain.entity

class PokeSpecieEntity(
        val id: Int,
        val name: String,
        val description: String,
        val varieties: List<PokeVarietyEntity>,
        val evolutionChain: String?
)