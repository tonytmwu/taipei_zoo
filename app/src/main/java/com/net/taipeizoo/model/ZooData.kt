package com.net.taipeizoo.model

import android.os.Parcelable
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class ZooData: Parcelable {
    @PrimaryKey
    @SerializedName("_id")
    var rid: Int = 0
    @SerializedName(value = "E_Name", alternate = ["F_Name_Ch", "A_Name_Ch"])
    var title: String? = null
    @SerializedName(value = "E_Category", alternate = ["F_Family", "A_Family"])
    var category: String? = null
    @SerializedName("E_Info", alternate = ["F_Feature", "A_Feature"])
    var info: String? = null
    @SerializedName("E_Pic_URL", alternate = ["F_Pic01_URL", "A_Pic01_URL"])
    var imgUrl: String? = null
    @Ignore
    var contentItem: ContentItem? = null

    companion object {
        val mockData get() = run {
            (0..10).map {
                ZooData()
            }
        }
    }
}