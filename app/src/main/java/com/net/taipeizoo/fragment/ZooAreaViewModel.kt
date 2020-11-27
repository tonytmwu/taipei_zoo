package com.net.taipeizoo.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.launch

class ZooAreaViewModel : ViewModel() {

    private val zooDataService by lazy { ZooDataService() }

    val zooAreas = zooDataService.zooAreas

    fun fetchZoomAreas() {
        viewModelScope.launch {
            zooDataService.fetchZooAreas()
        }
    }

}