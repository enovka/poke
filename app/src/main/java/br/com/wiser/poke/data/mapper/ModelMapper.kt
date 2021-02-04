package br.com.wiser.poke.data.mapper

interface ModelMapper<S, T> {
    fun map(model: S): T
}