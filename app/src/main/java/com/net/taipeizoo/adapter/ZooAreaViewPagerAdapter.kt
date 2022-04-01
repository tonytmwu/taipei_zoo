package com.net.taipeizoo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.net.taipeizoo.fragment.ZooDetailListFragment
import com.net.taipeizoo.fragment.ZooDetailListFragment.ZooAreaDetailType
import com.net.taipeizoo.model.ZooArea

class ZooAreaViewPagerAdapter(fragment: Fragment, private val zooArea: ZooArea?): FragmentStateAdapter(fragment) {

    var dataset: List<ZooAreaDetailType>? = null

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }

    override fun createFragment(position: Int): Fragment {
        return ZooDetailListFragment.newInstance(zooArea, dataset?.get(position))
    }
}