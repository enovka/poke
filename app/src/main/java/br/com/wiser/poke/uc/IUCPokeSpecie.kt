package br.com.wiser.poke.uc

import br.com.wiser.poke.data.domain.entity.PokeSpecieEntity

interface IUCPokeSpecie {

    suspend fun load(name: String): PokeSpecieEntity
}