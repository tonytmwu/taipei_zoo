package com.net.taipeizoo.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val okHttpClient =
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)

    var client = Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl("https://data.taipei/api/v1/dataset/")
                .addConverterFactory(TrimBOMBodyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(OpenDataApiService::class.java)

}