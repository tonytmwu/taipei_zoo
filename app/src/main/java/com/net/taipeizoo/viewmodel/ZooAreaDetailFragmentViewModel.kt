package com.net.taipeizoo.viewmodel

import androidx.lifecycle.*
import com.net.taipeizoo.model.ContentItem
import com.net.taipeizoo.model.ZooData
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.repository.ZooDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZooAreaDetailFragmentViewModel : ViewModel() {

    private val zooDataService by lazy { ZooDataService() }
    private val _zooAreaName = MutableLiveData<String>()
    private var zooAreaCategory: String? = null
    private var zooAreaInfo: String? = null

    val zooPlants = _zooAreaName.switchMap { zooAreaName ->
        zooDataService.observeZooPlants(zooAreaName).switchMap { zooPlants ->
            liveData {
                val list = ArrayList<ZooPlant>()
                createZooPlantContentItem(zooAreaCategory, zooAreaInfo)?.let {
                    list.add(it)
                }
                list.addAll(zooPlants)
                emit(list as List<ZooPlant>)
            }
        }
    }

    fun startObserveZooPlants(zooAreaName: String, category: String?, info: String?) {
        zooAreaCategory = category
        zooAreaInfo = info
        _zooAreaName.postValue(zooAreaName)
    }

    suspend fun getZooPlant(id: Int, zooPlants: List<ZooPlant>?): ZooPlant? {
        return withContext(Dispatchers.Default) {
            zooPlants?.firstOrNull { it.rid == id }
        }
    }

    private fun createZooPlantContentItem(title: String?, content: String?): ZooPlant? {
        return if(title?.isNotEmpty() == true && content?.isNotEmpty() == true) {
            ZooPlant().apply { contentItem = ContentItem(title, content) }
        } else { null }
    }
}