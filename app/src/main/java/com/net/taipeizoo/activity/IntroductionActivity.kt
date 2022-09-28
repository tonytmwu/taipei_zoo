package com.net.taipeizoo.activity

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.net.taipeizoo.activity.contract.IntroductionActivityResultContract.Companion.EXTRA_DATA
import com.net.taipeizoo.activity.contract.IntroductionActivityResultContract.Companion.EXTRA_DATA_TYPE
import com.net.taipeizoo.databinding.ActivityIntroductionBinding
import com.net.taipeizoo.fragment.IntroductionFragment

class IntroductionActivity : FragmentActivity(),
    IntroductionFragment.ZooPlantDetailFragmentListener {

    private var _vb: ActivityIntroductionBinding? = null
    private val vb get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(vb.root)

        showIntroductionFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    private fun showIntroductionFragment() {
        val zooDataType = intent?.getStringExtra(EXTRA_DATA_TYPE)
        val data = intent?.getStringExtra(EXTRA_DATA)

        supportFragmentManager.beginTransaction().apply {
            this.replace(vb.frameIntroduction.id, IntroductionFragment.newInstance(zooDataType, data))
            this.commitAllowingStateLoss()
        }
    }

    override fun backToZooAreaDetail() {
        finish()
    }
}