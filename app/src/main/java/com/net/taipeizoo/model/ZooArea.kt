package com.net.taipeizoo.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ZooArea (
    @SerializedName("e_no")
    val no: String? = null,
    @SerializedName("e_memo")
    val memo: String? = null,
    @SerializedName("e_geo")
    val geo: String? = null,
    @SerializedName("e_url")
    val url: String? = null
): ZooData(), Parcelable