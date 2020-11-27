package com.net.taipeizoo.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.launch

class ZooAreaDetailViewModel : ViewModel() {
    private val zooDataService by lazy { ZooDataService() }

    private val _zooPlants = MutableLiveData<List<ZooPlant>>()
    val zooPlants: LiveData<List<ZooPlant>> = _zooPlants

    fun fetchZooPlants() {
        viewModelScope.launch {
            _zooPlants.postValue(zooDataService.fetchZooPlant())
        }
    }
}