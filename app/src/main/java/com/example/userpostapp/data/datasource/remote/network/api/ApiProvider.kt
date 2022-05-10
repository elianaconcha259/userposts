package com.example.userpostapp.data.datasource.remote.network.api

import com.example.userpostapp.data.datasource.remote.network.url.URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().apply { connectTimeout(60, TimeUnit.SECONDS) }.build())
            .build()
    }

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)
}
