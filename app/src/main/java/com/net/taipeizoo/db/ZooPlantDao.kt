package com.net.taipeizoo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.net.taipeizoo.model.ZooPlant

@Dao
interface ZooPlantDao {

    @Query("select * from ZooPlant where location like '%' || :zooAreaName || '%'")
    fun observeZooPlant(zooAreaName: String): LiveData<List<ZooPlant>>

    @Query("select * from ZooPlant where location like '%' || :zooAreaName || '%'")
    suspend fun query(zooAreaName: String): List<ZooPlant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zooPlants: List<ZooPlant>)

}