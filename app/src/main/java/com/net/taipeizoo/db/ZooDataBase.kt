package com.net.taipeizoo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.model.ZooPlant

@Database(entities = [ZooArea::class, ZooPlant::class], version = 1)
abstract class ZooDataBase: RoomDatabase() {

    abstract fun zooAreaDao(): ZooAreaDao

    companion object {
        private var instance: ZooDataBase? = null

        fun initWith(context: Context) {
            if (instance == null) {
                synchronized(ZooDataBase::class) {
                    instance = Room.databaseBuilder(context,
                        ZooDataBase::class.java, "zoo.db")
                        .build()
                }
            }
        }

        fun get(): ZooDataBase {
            return instance!!
        }
    }

}