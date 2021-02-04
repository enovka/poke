package br.com.wiser.poke.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import br.com.wiser.poke.R

//TODO Colocar view de erro
class LoadingStateViewHolder(parent: ViewGroup, retry: () -> Unit) :
        RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.loading_content, parent, false)
        ) {

    private val imgLoading = itemView.findViewById<AppCompatImageView>(R.id.img_loading)
            .also {
                it.setOnClickListener { retry() }
            }

    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                imgLoading.visibility = View.GONE
            }
            is LoadState.Loading -> {
                imgLoading.visibility = View.VISIBLE
            }
            else -> {
                imgLoading.visibility = View.GONE
            }
        }
    }
}