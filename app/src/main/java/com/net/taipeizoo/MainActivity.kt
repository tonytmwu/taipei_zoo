package com.net.taipeizoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.net.taipeizoo.fragment.ZooAreaFragment
import com.net.taipeizoo.fragment.ZooAreaFragmentDirections
import com.net.taipeizoo.model.ZooArea

class MainActivity : AppCompatActivity(), ZooAreaFragment.ZooAreaFragmentListener {

    private val gson by lazy { Gson() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showDetail(data: ZooArea) {
        val json = gson.toJson(data)
        ZooAreaFragmentDirections.navToZooAreaDetailFragment(json)
    }
}