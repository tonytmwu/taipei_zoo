package com.net.taipeizoo.api

import com.google.gson.Gson
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.model.ZooPlantResult
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.net.URLDecoder
import java.net.URLEncoder

class TrimBOMBodyConverterFactory: Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>,
                                       retrofit: Retrofit): Converter<ResponseBody, *>? {

        return if (ZooPlantResult::class.java == type) {
            Converter<ResponseBody, Any> { body ->
                val byteOrderMarker = "%EF%BB%BF"
                val response = body.byteStream().bufferedReader().use { it.readText() }
                val encoded = URLEncoder.encode(response, Charsets.UTF_8.name())
                val processed = encoded.replace(byteOrderMarker, "")
                val decoded = URLDecoder.decode(processed, Charsets.UTF_8.name())
                Gson().fromJson(decoded, ZooPlantResult::class.java)
            }
        } else {
            null
        }
    }

}