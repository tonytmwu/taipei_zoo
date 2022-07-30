package com.net.taipeizoo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.net.taipeizoo.model.ZooAnimal
import com.net.taipeizoo.model.ZooPlant

@Dao
interface ZooAnimalDao {

    @Query("select * from ZooAnimal where location like '%' || :zooAreaName || '%'")
    fun observeZooAnimal(zooAreaName: String): LiveData<List<ZooAnimal>>

    @Query("select * from ZooAnimal")
    suspend fun query(): List<ZooAnimal>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zooAnimals: List<ZooAnimal>)

    @Query("DELETE FROM ZooAnimal")
    suspend fun clean()

    @Query("select * from ZooAnimal where location like '%' || :zooAreaName || '%'")
    suspend fun fetchZooAnimal(zooAreaName: String): List<ZooAnimal>

}