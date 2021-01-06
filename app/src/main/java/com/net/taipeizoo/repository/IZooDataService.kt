package com.net.taipeizoo.repository

import androidx.lifecycle.LiveData
import com.net.taipeizoo.api.OpenDataApiService
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.model.ZooPlant

interface IZooDataService {

    fun observeZooPlants(zooAreaName: String): LiveData<List<ZooPlant>>

    suspend fun fetchZooAreas(rid: String = OpenDataApiService.zooAreaRId): List<ZooArea>?

    suspend fun fetchZooPlant(rid: String = OpenDataApiService.zooPlantRId): List<ZooPlant>?

}