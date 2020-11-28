package com.net.taipeizoo.viewmodel

import androidx.lifecycle.ViewModel
import com.net.taipeizoo.repository.ZooDataService

class ZooAreaFragmentViewModel : ViewModel() {

    private val zooDataService by lazy { ZooDataService() }

    val zooAreas = zooDataService.zooAreas

}