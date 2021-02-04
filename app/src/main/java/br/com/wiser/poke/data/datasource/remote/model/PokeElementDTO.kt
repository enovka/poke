package br.com.wiser.poke.data.datasource.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeElementDTO(val name: String = "", val url: String?)