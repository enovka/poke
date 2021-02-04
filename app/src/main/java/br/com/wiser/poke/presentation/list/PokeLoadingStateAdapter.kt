package br.com.wiser.poke.presentation.list

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PokeLoadingStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadingStateViewHolder(parent, retry)

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) = holder.bind(loadState)
}