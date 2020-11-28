package com.net.taipeizoo.repository

import androidx.lifecycle.LiveData
import com.net.taipeizoo.api.OpenDataApiService
import com.net.taipeizoo.api.RetrofitClient
import com.net.taipeizoo.db.ZooDataBase
import com.net.taipeizoo.model.ZooPlant

class ZooDataService {

    private val apiClient = RetrofitClient.client
    private val zooAreaDao = ZooDataBase.get().zooAreaDao()
    private val zooPlantDao = ZooDataBase.get().zooPlantDao()

    val zooAreas = zooAreaDao.observeZooArea()

    fun observeZooPlants(zooAreaName: String): LiveData<List<ZooPlant>> {
        return zooPlantDao.observeZooPlant(zooAreaName)
    }

    suspend fun fetchZooAreas(rid: String = OpenDataApiService.zoomAreaRId) = execute {
        apiClient.fetchZooArea(rid)?.result?.results?.let { zooAreas ->
            zooAreaDao.insert(zooAreas)
            zooAreas
        }
    }

    suspend fun fetchZooPlant(rid: String = OpenDataApiService.zoomPlantRId) = execute {
        apiClient.fetchZooPlant(rid)?.result?.results?.let { zooPlants ->
            zooPlantDao.insert(zooPlants)
            zooPlants
        }
    }

    suspend fun getZooPlants(zooAreaName: String): List<ZooPlant> {
        return zooPlantDao.query(zooAreaName)
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