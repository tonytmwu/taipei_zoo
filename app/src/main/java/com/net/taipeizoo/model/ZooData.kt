package com.net.taipeizoo.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

open class ZooData {
    @PrimaryKey
    @SerializedName("_id")
    var rid: Int = 0
    @SerializedName(value = "E_Name", alternate = ["U+FEFFF_Name_Ch"])
    var title: String? = null
    @SerializedName(value = "E_Category", alternate = ["F_Family"])
    var category: String? = null
    @SerializedName("E_Info", alternate = ["F_Feature"])
    var info: String? = null
    @SerializedName("E_Pic_URL", alternate = ["F_Pic01_URL"])
    var imgUrl: String? = null

    companion object {
        val mockData get() = run {
            (0..10).map {
                ZooData()
            }
        }
    }
}