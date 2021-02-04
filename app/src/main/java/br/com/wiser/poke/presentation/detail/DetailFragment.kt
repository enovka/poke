package br.com.wiser.poke.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import br.com.wiser.poke.R
import br.com.wiser.poke.data.domain.entity.PokeDetailEntity
import com.bumptech.glide.Glide
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailFragment : Fragment() {

    private val varietyViewModel: VarietyViewModel by viewModel()
    private val speciesVM: SpecieViewModel by viewModel()

    private val statAdapter = StatAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        onEvents(speciesVM) { event ->
            when (val data = event.take()) {
                is SpecieEvent.SpeciesLoaded -> varietyViewModel.load(data.defaultVariety)
            }

        }

        onStates(varietyViewModel) { state ->
            when (state) {
                is VarietyState.Loading -> onVarietyLoading()
                is VarietyState.Error -> onVarietyError(state.error, state.name)
                is VarietyState.Loaded -> onVarietyLoaded(state.detail)
            }
        }

        return inflater.inflate(R.layout.content_detail, container,
                false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewStats.adapter = statAdapter

        if (savedInstanceState == null) {
            arguments?.getString(EXTRA_KEY, null)?.let {
                speciesVM.load(it)
            }
        }
    }

    private fun onVarietyLoading() {
        loadingDetailData()
        shimmerDetailContent.visibility = View.VISIBLE
        shimmerDetailContent.showShimmer(true)
    }

    private fun onVarietyError(error: Throwable, name: String) {
        shimmerDetailContent.hideShimmer()
        shimmerDetailContent.visibility = View.INVISIBLE
    }

    private fun onVarietyLoaded(poke: PokeDetailEntity) {
        shimmerDetailContent.hideShimmer()
        Glide.with(imageSprite)
                .load(poke.sprite)
                .placeholder(R.drawable.ic_noimage)
                .error(R.drawable.ic_noimage)
                .into(imageSprite)
        textPokeName.text = poke.name.capitalize(Locale.getDefault())
        textPokeId.text = getString(R.string.nr, poke.id)
        textWeightValue.text = getString(R.string.weight_format, poke.weight)
        textHeightValue.text = getString(R.string.height_format, poke.height)

        textPokeName.background = null
        textPokeId.background = null
        textWeightValue.background = null
        textHeightValue.background = null

        displayType(poke.types.getOrNull(0), textFirstType)
        displayType(poke.types.getOrNull(1), textSecondType)

        statAdapter.updateItems(poke.stats)

        scroll_detail.smoothScrollTo(0, 0)
    }

    private fun displayType(type: String?, txtView: AppCompatTextView) {
        type?.let {
            txtView.text = it
            txtView.visibility = View.VISIBLE
        } ?: run {
            txtView.text = ""
            txtView.visibility = View.INVISIBLE
        }
    }

    private fun loadingDetailData() {
        imageSprite.setImageResource(R.color.shimmerColor)

        textPokeName.text = ""
        textPokeId.text = ""
        textWeightValue.text = ""
        textHeightValue.text = ""
        displayType("", textFirstType)
        displayType("", textSecondType)

        statAdapter.updateItems(listOf(null, null, null, null, null, null))

        textPokeName.setBackgroundResource(R.color.shimmerColor)
        textPokeId.setBackgroundResource(R.color.shimmerColor)
        textWeightValue.setBackgroundResource(R.color.shimmerColor)
        textHeightValue.setBackgroundResource(R.color.shimmerColor)
    }



    companion object {
        const val TAG = "DetailFragment"
        private const val EXTRA_KEY = "NAME"

        fun newInstance(name: String): DetailFragment {
            val instance = DetailFragment()
            instance.arguments = Bundle().apply {
                putString(EXTRA_KEY, name)
            }
            return instance
        }
    }
}