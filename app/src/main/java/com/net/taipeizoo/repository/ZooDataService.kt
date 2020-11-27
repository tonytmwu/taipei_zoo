package com.net.taipeizoo.repository

import com.net.taipeizoo.api.OpenDataApiService
import com.net.taipeizoo.api.RetrofitClient

class ZooDataService {

    private val apiClient = RetrofitClient.client

    suspend fun fetchZooArea(rid: String = OpenDataApiService.zoomAreaRId) = execute { apiClient.fetchZooArea(rid) }

    suspend fun fetchZooPlant(rid: String = OpenDataApiService.zoomＰlantRId) = execute { apiClient.fetchZooPlant(rid) }

    private suspend fun <T> execute(action: suspend () -> T?): T? {
        return try {
            action()
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }

}