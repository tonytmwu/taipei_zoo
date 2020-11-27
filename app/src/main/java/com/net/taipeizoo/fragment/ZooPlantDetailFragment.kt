package com.net.taipeizoo.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.net.taipeizoo.databinding.FragmentZooPlantDetailBinding

class ZooPlantDetailFragment : Fragment() {

    interface ZooPlantDetailFragmentListener {
        fun backToZooAreaDetail()
    }

    private var _vb: FragmentZooPlantDetailBinding? = null
    private val vb get() = _vb!!
    private val vm : ZooPlantDetailViewModel by viewModels()
    private var listener: ZooPlantDetailFragmentListener? = null

    init {
        lifecycleScope.launchWhenStarted {
            setListener()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ZooPlantDetailFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = FragmentZooPlantDetailBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    private fun setListener() {
        vb.toolbar.setNavigationOnClickListener {
            listener?.backToZooAreaDetail()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance() = ZooPlantDetailFragment()
    }

}