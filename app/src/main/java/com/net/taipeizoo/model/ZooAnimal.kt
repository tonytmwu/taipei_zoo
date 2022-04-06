package com.net.taipeizoo.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ZooAnimal(
    @SerializedName("A_Name_Latin")
    val nameLatin: String? = null,
    @SerializedName("F_Location", alternate = ["A_Location"])
    val location: String? = null,
    @SerializedName("A_Name_En")
    val nameEn: String? = null,
    @SerializedName("A_Phylum")
    val phylum: String? = null,
    @SerializedName("A_Order")
    val order: String? = null,
    @SerializedName("A_Behavior")
    val behavior: String? = null,
    @SerializedName("A_Interpretation")
    val interpretation: String? = null,
    @SerializedName("A_Distribution")
    val distribution: String? = null,
    @SerializedName("A_Diet")
    val diet: String? = null
) : ZooData()