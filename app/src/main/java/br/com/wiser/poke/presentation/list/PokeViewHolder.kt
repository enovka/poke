package br.com.wiser.poke.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wiser.poke.R
import br.com.wiser.poke.data.domain.entity.PokeEntity
import com.bumptech.glide.Glide
import java.util.*

class PokeViewHolder(parent: ViewGroup, private val onClick: (name: String) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_item_content, parent, false)
) {

    private val imgSprite: AppCompatImageView = itemView.findViewById(R.id.imgSprite)
    private val txtNumber: AppCompatTextView = itemView.findViewById(R.id.textId)
    private val txtName: AppCompatTextView = itemView.findViewById(R.id.textName)

    fun bind(poke: PokeEntity?) {
        poke?.let { pokemon ->
            itemView.setOnClickListener { onClick.invoke(pokemon.name) }
            Glide.with(imgSprite)
                    .load(pokemon.spriteUrl)
                    .placeholder(R.drawable.ic_noimage)
                    .error(R.drawable.ic_noimage)
                    .into(imgSprite)

            txtNumber.text = itemView.context.getString(R.string.nr, poke.index)
            txtName.text = pokemon.name.capitalize(Locale.getDefault())
        } ?: run {
            itemView.setOnClickListener(null)
            Glide.with(imgSprite).clear(imgSprite)
            txtName.text = ""
        }
    }
}