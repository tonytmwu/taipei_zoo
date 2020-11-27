package com.net.taipeizoo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.net.taipeizoo.model.ZooArea

@Dao
interface ZooAreaDao {

    @Query("select * from ZooArea")
    fun observeZooArea(): LiveData<List<ZooArea>>

    @Query("select * from ZooArea")
    suspend fun query(): List<ZooArea>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zooAreas: List<ZooArea>)

}