package com.net.taipeizoo.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.net.taipeizoo.R

class ZooAreaDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ZooAreaDetailFragment()
    }

    private lateinit var viewModel: ZooAreaDetailViewModel
    private val navArgs: ZooAreaDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zoo_area_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ZooAreaDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}