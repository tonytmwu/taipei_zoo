package com.net.taipeizoo.repository

import androidx.lifecycle.LiveData
import com.net.taipeizoo.api.OpenDataApiService
import com.net.taipeizoo.api.RetrofitClient
import com.net.taipeizoo.db.ZooDataBase
import com.net.taipeizoo.model.ZooAnimal
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.model.ZooPlant

class ZooDataService: IZooDataService {

    private val apiClient = RetrofitClient.client
    private val zooAreaDao = ZooDataBase.get().zooAreaDao()
    private val zooPlantDao = ZooDataBase.get().zooPlantDao()
    private val zooAnimalDao = ZooDataBase.get().zooAnimalDao()

    val zooAreas = zooAreaDao.observeZooArea()

    override fun observeZooPlants(zooAreaName: String): LiveData<List<ZooPlant>> {
        return zooPlantDao.observeZooPlant(zooAreaName)
    }

    override fun observeZooAnimals(zooAreaName: String): LiveData<List<ZooAnimal>> {
        return zooAnimalDao.observeZooAnimal(zooAreaName)
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

    override suspend fun fetchZooAnimal(rid: String): List<ZooAnimal>? {
        return execute { apiClient.fetchZooAnimal(rid)?.result?.results?.let { zooAnimals ->
            zooAnimalDao.insert(zooAnimals)
            zooAnimals
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