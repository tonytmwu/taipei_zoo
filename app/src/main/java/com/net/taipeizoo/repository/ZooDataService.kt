package com.net.taipeizoo.repository

import androidx.lifecycle.LiveData
import com.net.taipeizoo.api.OpenDataApiService
import com.net.taipeizoo.api.RetrofitClient
import com.net.taipeizoo.db.ZooDataBase
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.model.ZooPlant

class ZooDataService: IZooDataService {

    private val apiClient = RetrofitClient.client
    private val zooAreaDao = ZooDataBase.get().zooAreaDao()
    private val zooPlantDao = ZooDataBase.get().zooPlantDao()

    val zooAreas = zooAreaDao.observeZooArea()

    override fun observeZooPlants(zooAreaName: String): LiveData<List<ZooPlant>> {
        return zooPlantDao.observeZooPlant(zooAreaName)
    }

    override suspend fun fetchZooAreas(rid: String): List<ZooArea>? {
        return execute { apiClient.fetchZooArea(rid)?.result?.results?.let { zooAreas ->
            zooAreaDao.insert(zooAreas)
            zooAreas
        }}
    }

    override suspend fun fetchZooPlant(rid: String): List<ZooPlant>? {
        return execute { apiClient.fetchZooPlant(rid)?.result?.results?.let { zooPlants ->
            zooPlantDao.insert(zooPlants)
            zooPlants
        }}
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