package com.net.taipeizoo.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
) : ZooData() {

    companion object {
        suspend fun toAnimals(csv: List<String?>): List<ZooAnimal> {
            return withContext(Dispatchers.Default) {
                csv.filterNotNull().mapIndexed { index, it ->
                    val arr = it.split("*")
                    ZooAnimal(
                        nameLatin = arr[8],
                        location = arr[5],
                        nameEn = arr[7],
                        phylum = arr[9],
                        order = arr[11],
                        behavior = arr[17],
                        interpretation = arr[20],
                        distribution = arr[14],
                        diet = arr[18]
                    ).apply {
                        rid = index + 1
                        title = arr[0]
                        category = arr[12]
                        info = arr[16]
                        imgUrl = arr[26]
                    }
                }
            }
        }
    }

}