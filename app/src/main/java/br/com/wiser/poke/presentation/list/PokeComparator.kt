package br.com.wiser.poke.presentation.list

import androidx.recyclerview.widget.DiffUtil
import br.com.wiser.poke.data.domain.entity.PokeEntity

class PokeComparator : DiffUtil.ItemCallback<PokeEntity>() {
    override fun areItemsTheSame(oldItem: PokeEntity, newItem: PokeEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokeEntity, newItem: PokeEntity): Boolean {
        return oldItem == newItem
    }
}