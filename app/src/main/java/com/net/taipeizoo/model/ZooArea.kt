package com.net.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class ZooArea(
    @SerializedName("_id")
    val id: Int = 0,
    @SerializedName("E_no")
    val no: String? = null,
    @SerializedName("E_Name")
    val name: String? = null,
    @SerializedName("E_Category")
    val category: String? = null,
    @SerializedName("E_Info")
    val info: String? = null,
    @SerializedName("E_Memo")
    val memo: String? = null,
    @SerializedName("E_Geo")
    val geo: String? = null,
    @SerializedName("E_Pic_URL")
    val imgUrl: String? = null,
    @SerializedName("E_URL")
    val url: String? = null
)