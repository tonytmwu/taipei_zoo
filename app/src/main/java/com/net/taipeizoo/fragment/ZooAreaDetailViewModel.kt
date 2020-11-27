package com.net.taipeizoo.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZooAreaDetailViewModel : ViewModel() {
    private val zooDataService by lazy { ZooDataService() }

    private val _zooPlants = MutableLiveData<List<ZooPlant>>()
    val zooPlants: LiveData<List<ZooPlant>> = _zooPlants

    fun fetchZooPlants(zooAreaName: String) {
        viewModelScope.launch {
            _zooPlants.postValue(zooDataService.fetchZooPlant(zooAreaName))
        }
    }

    suspend fun getZooPlant(id: Int): ZooPlant? {
        return withContext(Dispatchers.Default) {
            _zooPlants.value?.firstOrNull { it.rid == id }
        }
    }
}