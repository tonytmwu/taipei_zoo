package com.net.taipeizoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.net.taipeizoo.fragment.ZooAreaFragment
import com.net.taipeizoo.fragment.ZooAreaFragmentDirections
import com.net.taipeizoo.model.ZooArea

class MainActivity : AppCompatActivity(), ZooAreaFragment.ZooAreaFragmentListener {

    private val gson by lazy { Gson() }
    private lateinit var navController: NavController

    init {
        lifecycleScope.launchWhenStarted {
            navController = findNavController(R.id.fcvFragmentRoot)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showDetail(data: ZooArea) {
        val json = gson.toJson(data)
        val direction = ZooAreaFragmentDirections.navToZooAreaDetailFragment(json)
        navController.navigate(direction)
    }
}