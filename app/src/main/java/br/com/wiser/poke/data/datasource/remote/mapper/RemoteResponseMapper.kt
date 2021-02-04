package br.com.wiser.poke.data.datasource.remote.mapper

interface RemoteResponseMapper<S, T> {
    fun map(response: S): T
}