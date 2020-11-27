package com.net.taipeizoo.repository

import com.net.taipeizoo.api.OpenDataApiService
import com.net.taipeizoo.api.RetrofitClient

class ZooDataService {

    private val apiClient = RetrofitClient.client

    suspend fun fetchZooAreas(rid: String = OpenDataApiService.zoomAreaRId) = execute {
        apiClient.fetchZooArea(rid)?.result?.results
    }

    suspend fun fetchZooPlant(rid: String = OpenDataApiService.zoomPlantRId) = execute {
        apiClient.fetchZooPlant(rid)?.result?.results
    }

    private suspend fun <T> execute(action: suspend () -> T?): T? {
        return try {
            action()
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }

}