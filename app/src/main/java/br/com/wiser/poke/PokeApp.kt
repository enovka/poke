package br.com.wiser.poke

import android.app.Application
import br.com.wiser.poke.presentation.di.viewModelModule
import br.com.wiser.poke.tool.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokeApp)
            modules(listOf(
                    retrofitModule,
                    roomModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
            ))
        }
    }
}