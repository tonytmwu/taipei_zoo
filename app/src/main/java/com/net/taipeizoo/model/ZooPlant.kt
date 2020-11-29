package com.net.taipeizoo.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ZooPlant(
    @SerializedName("F_Name_Latin")
    val nameLatin: String? = null,
    @SerializedName("F_Location")
    val location: String? = null,
    @SerializedName("F_Name_En")
    val nameEn: String? = null,
    @SerializedName("F_Geo")
    val geo: String? = null,
    @SerializedName("F_Function＆Application")
    val function: String? = null,
    @SerializedName("F_Genus")
    val genus: String? = null,
    @SerializedName("F_Brief")
    val brief: String? = null,
    @SerializedName("F_AlsoKnown")
    val aliasName: String? = null
): ZooData()