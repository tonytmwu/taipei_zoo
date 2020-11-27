package com.net.taipeizoo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ZooArea(
    @SerializedName("E_no")
    val no: String? = null,
    @SerializedName("E_Memo")
    val memo: String? = null,
    @SerializedName("E_Geo")
    val geo: String? = null,
    @SerializedName("E_URL")
    val url: String? = null
): ZooData()