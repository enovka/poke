package br.com.wiser.poke.data.model

data class PokeDetailModel(
        val id: Int,
        val name: String,
        val height: Float,
        val weight: Float,
        val statuses: List<StatusModel>,
        val types: List<String>
)

data class StatusModel(
        val name: String,
        val value: Int
)