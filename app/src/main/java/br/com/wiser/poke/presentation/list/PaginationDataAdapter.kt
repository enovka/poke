package br.com.wiser.poke.presentation.list

import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

fun <T : Any, VH : RecyclerView.ViewHolder> PagingDataAdapter<T, VH>.withLoadStateLoaderHeaderFooter(
        loader: LoadStateAdapter<*>,
        header: LoadStateAdapter<*>,
        footer: LoadStateAdapter<*>
): ConcatAdapter {
    addLoadStateListener { loadStates ->
        header.loadState = loadStates.prepend
        footer.loadState = loadStates.append
        loader.loadState = loadStates.refresh
    }
    return ConcatAdapter(header, loader, this, footer)
}
