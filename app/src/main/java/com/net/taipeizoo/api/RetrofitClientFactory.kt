package com.net.taipeizoo.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientFactory {

    private val okHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

    fun <T> create(service: Class<T>): T {
        return Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl("https://data.taipei/opendata/")
            .addConverterFactory(TrimBOMBodyConverterFactory())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(service)
    }

}