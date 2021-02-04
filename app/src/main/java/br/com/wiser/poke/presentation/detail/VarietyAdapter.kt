package br.com.wiser.poke.presentation.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wiser.poke.data.domain.entity.PokeVarietyEntity

class VarietyAdapter(private val onItemClick: (name: String) -> Unit) : RecyclerView.Adapter<VarietyViewHolder>() {

    private var varieties: List<PokeVarietyEntity?> = emptyList()

    override fun onBindViewHolder(holder: VarietyViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VarietyViewHolder = VarietyViewHolder(parent, onItemClick)

    override fun getItemCount() = varieties.size

    fun updateItems(items: List<PokeVarietyEntity?>){
        varieties = items
        notifyDataSetChanged()
    }

    private fun getItem(index: Int): PokeVarietyEntity? {
        return if (index >= 0 && index < varieties.size) {
            varieties[index]
        } else {
            null
        }
    }
}

