package com.net.taipeizoo.api

import com.net.taipeizoo.model.ZooAreaResult
import com.net.taipeizoo.model.ZooPlantResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenDataApiService {

    companion object {
        const val zooAreaRId = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a"
        const val zooAnimalId = "5cb73231-b741-48b3-bec3-2ef57bb10029"
        const val zooPlantRId = "f18de02f-b6c9-47c0-8cda-50efad621c14"
    }

    @GET("{rid}")
    suspend fun fetchZooArea(@Path("rid") rid: String,
                             @Query("scope") scope: String = "resourceAquire"): ZooAreaResult?

    @GET("{rid}")
    suspend fun fetchZooPlant(@Path("rid") rid: String,
                              @Query("scope") scope: String = "resourceAquire"): ZooPlantResult?

}