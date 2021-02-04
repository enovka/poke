package br.com.wiser.poke.uc

import br.com.wiser.poke.data.domain.entity.PokeDetailEntity

interface IUCPokeDetail {
    suspend fun load(name: String): PokeDetailEntity
}