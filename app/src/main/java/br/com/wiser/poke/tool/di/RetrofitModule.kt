package br.com.wiser.poke.tool.di

import br.com.wiser.poke.BuildConfig
import br.com.wiser.poke.data.datasource.remote.PokeApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofitModule = module {
    single { buildOkHttpClient() }
    factory { buildMoshi() }
    single { createApiImpl(PokeApi::class.java, client = get(), "https://pokeapi.co/api/v2/") }
}


private fun buildOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    val baseLogger = HttpLoggingInterceptor()
                    baseLogger.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(baseLogger)
                }
            }.build()
}

private fun <S> createApiImpl(apiClass: Class<S>, client: OkHttpClient, baseUrl: String): S {
    val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    return retrofit.create(apiClass)

}

private fun buildMoshi(): Moshi {
    return Moshi.Builder()
        .build()
}
