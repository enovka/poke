package br.com.wiser.poke.presentation.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import br.com.wiser.poke.R
import br.com.wiser.poke.presentation.detail.DetailActivity
import br.com.wiser.poke.presentation.detail.DetailFragment
import io.uniflow.androidx.flow.onEvents
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val viewModel: PokeListViewModel by viewModel()
    private val adapter = PokeAdapter { name -> openDetail(name) }
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        twoPane = findViewById<View>(R.id.contentDetail) != null

        configRecyclerView()
        configSearchView()
        configRefreshLayout()


        lifecycleScope.launch {
            viewModel.list.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        swipeRefreshList.isRefreshing = true
                    }
                    is LoadState.Error -> {
                        viewModel.dataRefreshed()
                        swipeRefreshList.isRefreshing = false
                        pokeRecyclerView.visibility = View.INVISIBLE
                    }
                    is LoadState.NotLoading -> {
                        viewModel.dataRefreshed()
                        swipeRefreshList.isRefreshing = false
                        pokeRecyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }

        onEvents(viewModel) { event ->
            when (event.take()) {
                is PokeListEvent.QueryChanged -> adapter.refresh()
                is PokeListEvent.RefreshContent -> {
                    closeDetail()
                    adapter.refresh()
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                viewModel.refreshData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configRecyclerView() {

        pokeRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PokeLoadingStateAdapter(adapter::retry),
                footer = PokeLoadingStateAdapter(adapter::retry)
        )

        pokeRecyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

    }

    private fun configSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.applyFilter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.applyFilter(newText)
                }
                return false
            }
        })
    }

    private fun configRefreshLayout() {
        swipeRefreshList.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    private fun openDetail(name: String) {
        if (twoPane) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.contentDetail, DetailFragment.newInstance(name), DetailFragment.TAG)
                    .commit()
        } else {
            startActivity(DetailActivity.getStartIntent(this, name))
        }
    }

    private fun closeDetail() {
        if (twoPane) {
            supportFragmentManager.findFragmentByTag(DetailFragment.TAG)?.let {
                supportFragmentManager
                        .beginTransaction()
                        .remove(it)
                        .commit()
            }
        }
    }

    companion object {
        fun getStartIntent(context: Context) =
                Intent(context, ListActivity::class.java)
    }
}