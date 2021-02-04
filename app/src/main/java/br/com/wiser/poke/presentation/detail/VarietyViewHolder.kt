package br.com.wiser.poke.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wiser.poke.R
import br.com.wiser.poke.data.domain.entity.PokeVarietyEntity
import com.bumptech.glide.Glide
import java.util.*

class VarietyViewHolder(parent: ViewGroup, private val onClick: (name: String) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.variety_item_content, parent, false)
) {

    private val imgSprite: AppCompatImageView = itemView.findViewById(R.id.imageVarietySprite)
    private val txtName: AppCompatTextView = itemView.findViewById(R.id.textVarietyName)

    fun bind(poke: PokeVarietyEntity?) {
        poke?.let { variety ->
            itemView.setOnClickListener { onClick.invoke(variety.pokemon.name) }
            Glide.with(imgSprite)
                    .load(variety.pokemon.spriteUrl)
                    .placeholder(R.drawable.ic_noimage)
                    .error(R.drawable.ic_noimage)
                    .into(imgSprite)

            txtName.text = variety.pokemon.name.capitalize(Locale.getDefault())
            txtName.background = null
        } ?: run {
            itemView.setOnClickListener(null)
            imgSprite.setImageResource(R.color.shimmerColor)
            imgSprite.background = null
            txtName.text = ""
            txtName.setBackgroundResource(R.color.shimmerColor)
        }
    }
}