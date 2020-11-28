package com.net.taipeizoo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    private val service by lazy { ZooDataService() }

    fun fetchZooArea() {
        viewModelScope.launch {
            service.fetchZooAreas()
        }
    }

    fun fetchZooPlant() {
        viewModelScope.launch {
            service.fetchZooAreas()
        }
    }

}