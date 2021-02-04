package br.com.wiser.poke.data.datasource.local.mapper

interface ToDatabaseMapper<S, T> {
    fun toDatabase(model: T): S
}