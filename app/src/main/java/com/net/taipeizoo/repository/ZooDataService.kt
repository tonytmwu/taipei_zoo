package com.net.taipeizoo.repository

import androidx.lifecycle.LiveData
import com.net.taipeizoo.api.OpenDataApiRxService
import com.net.taipeizoo.api.RetrofitClient
import com.net.taipeizoo.api.RetrofitClientFactory
import com.net.taipeizoo.db.ZooDataBase
import com.net.taipeizoo.model.ZooPlant
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ZooDataService : IZooDataService {

    private val apiClient = RetrofitClient.client
    private val zooAreaDao = ZooDataBase.get().zooAreaDao()
    private val zooPlantDao = ZooDataBase.get().zooPlantDao()

    private val rxApiClient = RetrofitClientFactory.create(OpenDataApiRxService::class.java)

    val zooAreas = zooAreaDao.observeZooArea()

    override fun observeZooPlants(zooAreaName: String): LiveData<List<ZooPlant>> {
        return zooPlantDao.observeZooPlant(zooAreaName)
    }

    override fun fetchZooAreas(rid: String) {
        rxApiClient.fetchZooArea(rid)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeBy {
                it.body()?.result?.results?.let { zooAreas ->
                    GlobalScope.launch {
                        zooAreaDao.insert(zooAreas)
                    }
                }
            }
    }

    override suspend fun fetchZooPlant(rid: String): List<ZooPlant>? {
        return execute {
            apiClient.fetchZooPlant(rid)?.result?.results?.let { zooPlants ->
                zooPlantDao.insert(zooPlants)
                zooPlants
            }
        }
    }

    private suspend fun <T> execute(action: suspend () -> T?): T? {
        return try {
            action()
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }

}