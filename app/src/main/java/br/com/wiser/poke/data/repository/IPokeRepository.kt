package br.com.wiser.poke.data.repository

import br.com.wiser.poke.data.domain.entity.PokeDetailEntity
import br.com.wiser.poke.data.domain.entity.PokeListPageEntity
import br.com.wiser.poke.data.domain.entity.PokeSpecieEntity

interface IPokeRepository {

    suspend fun getList(query: String?, page: Int, pageSize: Int, refresh: Boolean = false) : PokeListPageEntity
    suspend fun getDetailByName(name: String) : PokeDetailEntity
    suspend fun getSpeciesByName(name: String) : PokeSpecieEntity
}