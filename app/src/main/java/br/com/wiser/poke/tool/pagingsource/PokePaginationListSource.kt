package br.com.wiser.poke.tool.pagingsource

import androidx.paging.PagingSource
import br.com.wiser.poke.data.domain.entity.PokeEntity
import br.com.wiser.poke.uc.IUCPokeListPage

class PokePaginationListSource(private val query: String?, private val refresh: Boolean = false, private val getListPage: IUCPokeListPage) : PagingSource<Int, PokeEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeEntity> {
        val page = params.key ?: 0
        val size = params.loadSize
        return try {
            val response = getListPage.load(query, page, size, refresh)
            LoadResult.Page(
                    data = response.results,
                    prevKey = response.previous,
                    nextKey = response.next
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}