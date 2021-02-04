package br.com.wiser.poke.tool.component

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import br.com.wiser.poke.R
import br.com.wiser.poke.data.datasource.remote.RemotekException

object ExceptionViewTool {
    fun showError(throwable: Throwable, text: AppCompatTextView, imageView: AppCompatImageView) {
        when (throwable) {
            is RemotekException -> {
                text.setText(R.string.error_connection)
                imageView.setImageResource(R.drawable.ic_error)
            }
            else -> {
                text.setText(R.string.error_generic)
                imageView.setImageResource(R.drawable.ic_error)
            }
        }
    }
}