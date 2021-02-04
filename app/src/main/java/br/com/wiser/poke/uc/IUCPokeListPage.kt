package br.com.wiser.poke.uc

import br.com.wiser.poke.data.domain.entity.PokeListPageEntity

interface IUCPokeListPage {
    suspend fun load(query: String?, page: Int, pageSize: Int, refresh: Boolean = false): PokeListPageEntity
}