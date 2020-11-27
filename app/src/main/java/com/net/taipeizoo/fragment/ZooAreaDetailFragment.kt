package com.net.taipeizoo.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.net.taipeizoo.R
import com.net.taipeizoo.databinding.FragmentZooAreaDetailBinding

class ZooAreaDetailFragment : Fragment() {

    private var _vb: FragmentZooAreaDetailBinding? = null
    private val vb get() = _vb!!
    private val vm = viewModels<ZooAreaDetailViewModel>()
    private val navArgs: ZooAreaDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = FragmentZooAreaDetailBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    companion object {
        fun newInstance() = ZooAreaDetailFragment()
    }

}