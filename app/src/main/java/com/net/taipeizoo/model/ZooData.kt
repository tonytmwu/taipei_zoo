package com.net.taipeizoo.model

import android.os.Parcelable
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class ZooData: Parcelable {
    @PrimaryKey
    @SerializedName(value = "_id", alternate = ["A_CID"])
    var rid: Int = 0
    @SerializedName(value = "e_name", alternate = ["F_Name_Ch", "A_Name_Ch"])
    var title: String? = null
    @SerializedName(value = "e_category", alternate = ["F_Family", "A_Family"])
    var category: String? = null
    @SerializedName("e_info", alternate = ["F_Feature", "A_Feature"])
    var info: String? = null
    @SerializedName("e_pic_url", alternate = ["F_Pic01_URL", "A_Pic01_URL"])
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