package com.net.taipeizoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.net.taipeizoo.databinding.ActivityMainBinding
import com.net.taipeizoo.fragment.ZooAreaDetailFragment
import com.net.taipeizoo.fragment.ZooAreaFragment
import com.net.taipeizoo.fragment.ZooAreaFragmentDirections
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.model.ZooData

class MainActivity : AppCompatActivity(),
    ZooAreaFragment.ZooAreaFragmentListener,
    ZooAreaDetailFragment.ZooAreaDetailFragmentListener {

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

    override fun goBack() {
        popBackStack()
    }
}