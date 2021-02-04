package br.com.wiser.poke.data.model

class SpecieModel(
        val id: Int,
        val name: String,
        val description: String,
        val varieties: List<VarietyModel>,
        val evolutionChain: String?
)