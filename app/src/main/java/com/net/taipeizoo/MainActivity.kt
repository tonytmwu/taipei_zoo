package com.net.taipeizoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.net.taipeizoo.databinding.ActivityMainBinding
import com.net.taipeizoo.fragment.*
import com.net.taipeizoo.model.ZooData
import com.net.taipeizoo.model.ZooPlant

class MainActivity : AppCompatActivity(),
    ZooAreaFragment.ZooAreaFragmentListener,
    ZooAreaDetailFragment.ZooAreaDetailFragmentListener,
    ZooPlantDetailFragment.ZooPlantDetailFragmentListener{

    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!
    private val gson by lazy { Gson() }
    private lateinit var navController: NavController

    init {
        lifecycleScope.launchWhenStarted {
            navController = findNavController(R.id.fcvFragmentRoot)
            setListener()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
    }

    private fun setListener() {
        navController.addOnDestinationChangedListener { _, _, _ ->
            when(navController.previousBackStackEntry) {
                null -> supportActionBar?.show()
                else -> supportActionBar?.hide()
            }
        }
    }

    private fun popBackStack() {
        navController.navigateUp()
    }

    override fun showDetail(data: ZooData) {
        val json = gson.toJson(data)
        val direction = ZooAreaFragmentDirections.navToZooAreaDetailFragment(json)
        navController.navigate(direction)
    }

    override fun showZooPlantDetail(data: ZooPlant) {
        val json = gson.toJson(data)
        Log.d(javaClass.simpleName, json)
        val direction = ZooAreaDetailFragmentDirections.navToZooPlantDetailFragment(json)
        navController.navigate(direction)
    }

    override fun backToZooArea() {
        popBackStack()
    }

    override fun backToZooAreaDetail() {
        popBackStack()
    }
}