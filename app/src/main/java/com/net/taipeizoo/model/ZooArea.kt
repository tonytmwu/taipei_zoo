package com.net.taipeizoo.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ZooArea (
    @SerializedName("E_no")
    val no: String? = null,
    @SerializedName("E_Memo")
    val memo: String? = null,
    @SerializedName("E_Geo")
    val geo: String? = null,
    @SerializedName("E_URL")
    val url: String? = null
): ZooData(), Parcelable