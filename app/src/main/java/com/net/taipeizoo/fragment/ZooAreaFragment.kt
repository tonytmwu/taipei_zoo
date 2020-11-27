package com.net.taipeizoo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.net.taipeizoo.databinding.FragmentZooAreaBinding

class ZooAreaFragment : Fragment() {

    private var _vb: FragmentZooAreaBinding? = null
    private val vb get() = _vb!!

    private val vm: ZooAreaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = FragmentZooAreaBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindLiveData()
        vm.fetchZoomAreas()
    }

    private fun bindLiveData() {
        vm.zooAreas.observe(viewLifecycleOwner) { zooAreas ->
            println("zooAreas = ${zooAreas.size}")
        }
    }

    companion object {
        fun newInstance() = ZooAreaFragment()
    }

}