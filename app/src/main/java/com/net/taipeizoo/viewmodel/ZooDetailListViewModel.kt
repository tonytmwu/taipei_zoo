package com.net.taipeizoo.viewmodel

import androidx.lifecycle.*
import com.net.taipeizoo.model.ZooAnimal
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ZooDetailListViewModel : ViewModel() {

    private val _zooAreaName = MutableStateFlow<String?>(null)
    private val zooDataService = ZooDataService()

    fun collectZooAreaPlants(zooAreaName: String): Flow<List<ZooPlant>?> {
        return zooDataService.observeZooPlants(zooAreaName).asFlow()
    }

    fun collectZooAreaAnimals(zooAreaName: String): Flow<List<ZooAnimal>?> {
        return zooDataService.observeZooAnimals(zooAreaName).asFlow()
    }

}