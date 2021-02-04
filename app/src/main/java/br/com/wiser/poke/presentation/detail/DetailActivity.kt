package br.com.wiser.poke.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.wiser.poke.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            intent.extras?.getString(EXTRA_KEY)?.let {
                openDetail(it)
            }
        }
    }

    private fun openDetail(name: String) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentDetail, DetailFragment.newInstance(name))
                .commit()
    }

    companion object {
        private const val EXTRA_KEY = "NAME"

        fun getStartIntent(context: Context, name: String) =
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(EXTRA_KEY, name)
                }
    }
}