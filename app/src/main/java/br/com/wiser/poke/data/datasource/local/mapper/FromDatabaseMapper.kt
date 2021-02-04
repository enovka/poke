package br.com.wiser.poke.data.datasource.local.mapper

interface FromDatabaseMapper<S, T> {
    fun fromDatabase(entity: S): T
}