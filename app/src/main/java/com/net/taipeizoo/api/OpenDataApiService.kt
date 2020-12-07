package com.net.taipeizoo.api

import com.net.taipeizoo.model.ZooAreaResult
import com.net.taipeizoo.model.ZooPlantResult
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDataApiService {

    @GET("datalist/apiAccess")
    suspend fun fetchZooArea(@Query("rid") rid: String,
                             @Query("scope") scope: String = "resourceAquire"): ZooAreaResult?

    @GET("datalist/apiAccess")
    suspend fun fetchZooPlant(@Query("rid") rid: String,
                              @Query("scope") scope: String = "resourceAquire"): ZooPlantResult?

}