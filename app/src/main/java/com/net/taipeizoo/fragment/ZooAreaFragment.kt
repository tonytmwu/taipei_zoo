package com.net.taipeizoo.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.net.taipeizoo.R
import com.net.taipeizoo.databinding.ZooAreaFragmentBinding

class ZooAreaFragment : Fragment() {

    private var _vb: ZooAreaFragmentBinding? = null
    private val vb get() = _vb!!

    companion object {
        fun newInstance() = ZooAreaFragment()
    }

    private lateinit var viewModel: ZooAreaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = ZooAreaFragmentBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ZooAreaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}