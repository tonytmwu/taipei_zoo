package com.net.taipeizoo.api

import com.net.taipeizoo.model.ZooAreaResult
import com.net.taipeizoo.model.ZooPlantResult
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDataApiService {

    companion object {
        const val zoomAreaRId = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a"
        const val zoomPlantRId = "f18de02f-b6c9-47c0-8cda-50efad621c14"
    }

    @GET("datalist/apiAccess")
    suspend fun fetchZooArea(@Query("rid") rid: String,
                                   @Query("scope") scope: String = "resourceAquire"): ZooAreaResult?

    @GET("datalist/apiAccess")
    suspend fun fetchZooPlant(@Query("rid") rid: String,
                             @Query("scope") scope: String = "resourceAquire"): ZooPlantResult?

}