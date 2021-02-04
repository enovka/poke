package br.com.wiser.poke.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wiser.poke.R
import br.com.wiser.poke.data.domain.entity.StatEntity
import java.util.*
import kotlin.math.min

class StatViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.stat_item_content, parent, false)
) {

    private val txtName: AppCompatTextView = itemView.findViewById(R.id.textStatName)
    private val prgValue: ProgressBar = itemView.findViewById(R.id.progressStat)

    fun bind(stat: StatEntity?) {
        stat?.let { it ->
            txtName.text = it.name.capitalize(Locale.getDefault())
            txtName.background = null
            prgValue.progress = min(it.value, prgValue.max)
        } ?: run {
            itemView.setOnClickListener(null)
            txtName.text = ""
            txtName.setBackgroundResource(R.color.shimmerColor)
            prgValue.progress = prgValue.max
        }
    }
}