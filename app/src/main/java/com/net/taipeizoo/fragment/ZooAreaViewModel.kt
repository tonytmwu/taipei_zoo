package com.net.taipeizoo.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.launch

class ZooAreaViewModel : ViewModel() {

    private val zooDataService by lazy { ZooDataService() }

    private val _zooAreas = MutableLiveData<List<ZooArea>>()
    val zooAreas: LiveData<List<ZooArea>> = _zooAreas
    val zooAreasDB = zooDataService.zooAreas

    fun fetchZoomAreas() {
        viewModelScope.launch {
            _zooAreas.postValue(zooDataService.fetchZooAreas())
        }
    }

}