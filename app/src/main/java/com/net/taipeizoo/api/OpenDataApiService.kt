package com.net.taipeizoo.api

import com.net.taipeizoo.model.ZooAnimalResult
import com.net.taipeizoo.model.ZooAreaResult
import com.net.taipeizoo.model.ZooPlantResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenDataApiService {

    companion object {
        const val zooAreaRId = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a"
        const val zooAnimalId = "a3e2b221-75e0-45c1-8f97-75acbd43d613"
        const val zooPlantRId = "f18de02f-b6c9-47c0-8cda-50efad621c14"
    }

    @GET("{rid}")
    suspend fun fetchZooArea(@Path("rid") rid: String,
                             @Query("scope") scope: String = "resourceAquire"): ZooAreaResult?

    @GET("{rid}")
    suspend fun fetchZooPlant(@Path("rid") rid: String,
                              @Query("scope") scope: String = "resourceAquire"): ZooPlantResult?

    @GET("{rid}")
    suspend fun fetchZooAnimal(@Path("rid") rid: String,
                              @Query("scope") scope: String = "resourceAquire", @Query("limit") limit: Int = 500): ZooAnimalResult?

}