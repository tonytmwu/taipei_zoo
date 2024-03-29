package com.net.taipeizoo

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.google.gson.Gson
import com.net.taipeizoo.activity.contract.IntroductionActivityResultContract
import com.net.taipeizoo.databinding.ActivityMainBinding
import com.net.taipeizoo.fragment.*
import com.net.taipeizoo.model.ZooData
import com.net.taipeizoo.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity(),
    ZooAreaFragment.ZooAreaFragmentListener,
    ZooAreaDetailFragment.ZooAreaDetailFragmentListener,
    ZooDetailListFragment.ZooDetailListFragmentListener,
    IntroductionFragment.ZooPlantDetailFragmentListener {

    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!
    private val vm: MainActivityViewModel by viewModels()
    private val gson by lazy { Gson() }
    private lateinit var navController: NavController
    private val onDestinationChangedListener = NavController.OnDestinationChangedListener { _, _, _ ->
        when(navController.previousBackStackEntry) {
            null -> supportActionBar?.show()
            else -> supportActionBar?.hide()
        }
    }

    init {
        lifecycleScope.launchWhenCreated {
            vm.fetchZooArea()
            vm.fetchZooPlant()
            vm.fetchZooAnimal()
        }

        lifecycleScope.launchWhenStarted {
            supportActionBar?.title = getString(R.string.toolbar_title)
            navController = findNavController(R.id.fcvFragmentRoot)
            setListener()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        removeListener()
        _vb = null
    }

    private fun setListener() {
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
    }

    private fun removeListener() {
        navController.removeOnDestinationChangedListener(onDestinationChangedListener)
    }

    private fun popBackStack() {
        navController.navigateUp()
    }

    override fun showDetail(data: ZooData, sharedElementView: View) {
        val json = gson.toJson(data)
        val direction = ZooAreaFragmentDirections.navToZooAreaDetailFragment(json)
        val extra = FragmentNavigatorExtras(
                sharedElementView to (data.imgUrl ?: "")
        )
        navController.navigate(direction, extra)
    }

    override fun backToZooArea() {
        popBackStack()
    }

    override fun backToZooAreaDetail() {
        popBackStack()
    }

    private val launcher =
        registerForActivityResult(IntroductionActivityResultContract()) {
            // do nothing
    }

    override fun showIntroduction(zooAreaDetailType: ZooDetailListFragment.ZooAreaDetailType?, data: ZooData, sharedElementView: View) {
        val json = gson.toJson(data)
        launcher.launch(IntroductionActivityResultContract.Input(zooDataType = zooAreaDetailType?.name ?: "", data = json))
//        val direction = ZooAreaDetailFragmentDirections.navToIntroductionFragment(zooAreaDetailType?.name ?: "", json)
//        val extra = FragmentNavigatorExtras(
//            sharedElementView to (data.imgUrl ?: "")
//        )
//        navController.navigate(direction, extra)
    }
}