package com.net.taipeizoo.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.gson.Gson
import com.net.taipeizoo.R
import com.net.taipeizoo.databinding.FragmentZooAreaDetailBinding
import com.net.taipeizoo.model.ZooArea

class ZooAreaDetailFragment : Fragment() {

    interface ZooAreaDetailFragmentListener {
        fun goBack()
    }

    private var _vb: FragmentZooAreaDetailBinding? = null
    private val vb get() = _vb!!
    private val vm = viewModels<ZooAreaDetailViewModel>()
    private val navArgs: ZooAreaDetailFragmentArgs by navArgs()
    private val gson by lazy { Gson() }
    private var zooArea: ZooArea? = null
    private var listener: ZooAreaDetailFragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = FragmentZooAreaDetailBinding.inflate(inflater, container, false)
        return vb.root
    }

    init {
        lifecycleScope.launchWhenStarted {
            processNavArgs()
            setListener()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ZooAreaDetailFragmentListener
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    private fun processNavArgs() {
        gson.fromJson(navArgs.zooArea, ZooArea::class.java)?.apply {
            zooArea = this
            vb.ivImg.load(imgUrl)
            vb.toolbar.title = name
            vb.tvInfo.text = info
            vb.tvCategory.text = category
        }
    }

    private fun setListener() {
        vb.toolbar.setNavigationOnClickListener {
            listener?.goBack()
        }
    }

    companion object {
        fun newInstance() = ZooAreaDetailFragment()
    }

}