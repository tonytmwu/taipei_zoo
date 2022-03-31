package com.net.taipeizoo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.net.taipeizoo.model.ZooAnimal

@Dao
interface ZooAnimalDao {

    @Query("select * from ZooAnimal")
    suspend fun query(): List<ZooAnimal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zooAnimals: List<ZooAnimal>)

}