package br.com.wiser.poke.uc.implementation

import br.com.wiser.poke.data.domain.entity.PokeListPageEntity
import br.com.wiser.poke.data.repository.IPokeRepository
import br.com.wiser.poke.uc.IUCPokeListPage

class UCPokeListPageImpl(private val repository: IPokeRepository) : IUCPokeListPage {

   override suspend fun load(query: String?, page: Int, pageSize: Int, refresh: Boolean): PokeListPageEntity {
        return repository.getList(query, page, pageSize, refresh)
    }
}