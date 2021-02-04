package br.com.wiser.poke.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import br.com.wiser.poke.data.domain.entity.PokeEntity
import br.com.wiser.poke.tool.pagingsource.PokePaginationListSource
import br.com.wiser.poke.uc.IUCPokeListPage
import io.uniflow.androidx.flow.AndroidDataFlow

class PokeListViewModel(IUCPokeListPage: IUCPokeListPage) : AndroidDataFlow() {

    private var query: String? = null
    private var refresh: Boolean = false

    private val factory: () -> PagingSource<Int, PokeEntity> = {
        PokePaginationListSource(query, refresh, IUCPokeListPage)
    }

    val list = Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = factory
    )
            .flow
            .cachedIn(viewModelScope)

    fun applyFilter(query: String?) {
        if (query != this.query) {
            this.query = query
            action { sendEvent(PokeListEvent.QueryChanged) }
        }
    }

    fun refreshData() {
        if (!refresh) {
            this.refresh = true
            action { sendEvent(PokeListEvent.RefreshContent) }
        }
    }

    fun dataRefreshed() {
        this.refresh = false
    }
}

