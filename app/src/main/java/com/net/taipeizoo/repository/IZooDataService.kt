package com.net.taipeizoo.repository

import androidx.lifecycle.LiveData
import com.net.taipeizoo.model.ZooPlant

interface IZooDataService {

    companion object {
        const val zoomAreaRId = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a"
        const val zoomPlantRId = "f18de02f-b6c9-47c0-8cda-50efad621c14"
    }

    fun observeZooPlants(zooAreaName: String): LiveData<List<ZooPlant>>

    fun fetchZooAreas(rid: String = zoomAreaRId)

    suspend fun fetchZooPlant(rid: String = zoomPlantRId): List<ZooPlant>?

}