package com.net.taipeizoo

import android.app.Application
import android.content.Context
import com.net.taipeizoo.db.ZooDataBase

class CoreApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        ZooDataBase.initWith(applicationContext)
        context = this.applicationContext
    }
}