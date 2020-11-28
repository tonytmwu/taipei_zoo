package com.net.taipeizoo.viewmodel

import androidx.lifecycle.*
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZooAreaDetailFragmentViewModel : ViewModel() {

    private val zooDataService by lazy { ZooDataService() }
    private val _zooAreaName = MutableLiveData<String>()

    val zooPlants = _zooAreaName.switchMap { zooAreaName ->
        zooDataService.observeZooPlants(zooAreaName)
    }

    fun startObserveZooPlants(zooAreaName: String) {
        _zooAreaName.postValue(zooAreaName)
    }

    suspend fun getZooPlant(id: Int, zooPlants: List<ZooPlant>?): ZooPlant? {
        return withContext(Dispatchers.Default) {
            zooPlants?.firstOrNull { it.rid == id }
        }
    }
}