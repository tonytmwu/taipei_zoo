package com.net.taipeizoo.api

import com.net.taipeizoo.model.ZooAreaResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDataApiRxService {

    @GET("datalist/apiAccess")
    fun fetchZooArea(@Query("rid") rid: String,
                     @Query("scope") scope: String = "resourceAquire"): Single<Response<ZooAreaResult?>>

}