package com.net.taipeizoo

import android.app.Application
import com.net.taipeizoo.db.ZooDataBase

class CoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ZooDataBase.initWith(applicationContext)
    }
}