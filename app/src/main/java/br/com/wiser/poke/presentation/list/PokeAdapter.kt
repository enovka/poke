package br.com.wiser.poke.presentation.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.wiser.poke.data.domain.entity.PokeEntity

class PokeAdapter(private val onItemClick: (name: String) -> Unit) : PagingDataAdapter<PokeEntity, PokeViewHolder>(
    PokeComparator()
) {

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder = PokeViewHolder(parent, onItemClick)
}

