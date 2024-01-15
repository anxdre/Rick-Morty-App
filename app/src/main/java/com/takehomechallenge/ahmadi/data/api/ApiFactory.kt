package com.takehomechallenge.ahmadi.data.api

import com.takehomechallenge.ahmadi.BuildConfig
import com.takehomechallenge.ahmadi.data.api.service.RickMortyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {
    private var rickMortyService: RickMortyService? = null


    private fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
        return retrofit.create(serviceClass)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    fun getRickMortyService(): RickMortyService {
        if (rickMortyService == null) {
            rickMortyService = createService(RickMortyService::class.java)
        }
        return rickMortyService!!
    }
}