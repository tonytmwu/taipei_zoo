package com.net.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class ZooPlant(
    @SerializedName("_id")
    val id: Int = 0,
    @SerializedName("F_Name_Latin")
    val nameLatin: String? = null,
    @SerializedName("F_Location")
    val location: String? = null,
    @SerializedName("F_Pic01_URL")
    val imgUrl: String? = null,
    @SerializedName("F_Name_En")
    val nameEn: String? = null,
    @SerializedName("F_Family")
    val family: String? = null,
    @SerializedName("F_Geo")
    val geo: String? = null,
    @SerializedName("F_Feature")
    val feature: String? = null,
    @SerializedName("F_Function＆Application")
    val function: String? = null,
    @SerializedName("F_Genus")
    val genus: String? = null,
    @SerializedName("F_Brief")
    val brief: String? = null,
    @SerializedName("F_Name_Ch")
    val nameCh: String? = null,
)