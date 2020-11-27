package com.net.taipeizoo.repository

import com.net.taipeizoo.api.OpenDataApiService
import com.net.taipeizoo.api.RetrofitClient
import com.net.taipeizoo.db.ZooDataBase

class ZooDataService {

    private val apiClient = RetrofitClient.client
    private val zooAreaDao = ZooDataBase.get().zooAreaDao()

    val zooAreas = zooAreaDao.observeZooArea()

    suspend fun fetchZooAreas(rid: String = OpenDataApiService.zoomAreaRId) = execute {
        apiClient.fetchZooArea(rid)?.result?.results
    }

    suspend fun fetchZooPlant(zooAreaName: String,
                              rid: String = OpenDataApiService.zoomPlantRId) = execute {
        apiClient.fetchZooPlant(rid)?.result?.results?.filter {
            it.location?.isNotEmpty() == true && it.location.contains(zooAreaName)
        }
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