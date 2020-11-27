package com.net.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class ZooArea(
    @SerializedName("_id")
    override val id: Int = 0,
    @SerializedName("E_no")
    val no: String? = null,
    @SerializedName("E_Name")
    override val name: String? = null,
    @SerializedName("E_Category")
    override val category: String? = null,
    @SerializedName("E_Info")
    override val info: String? = null,
    @SerializedName("E_Memo")
    val memo: String? = null,
    @SerializedName("E_Geo")
    val geo: String? = null,
    @SerializedName("E_Pic_URL")
    override val imgUrl: String? = null,
    @SerializedName("E_URL")
    val url: String? = null
): ZooData()