package com.net.taipeizoo.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.gson.Gson
import com.net.taipeizoo.adapter.ContentItemAdapter
import com.net.taipeizoo.databinding.FragmentZooPlantDetailBinding
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.view.DividerItemDecoration
import com.net.taipeizoo.viewmodel.ZooPlantDetailFragmentViewModel

class ZooPlantDetailFragment : Fragment() {

    interface ZooPlantDetailFragmentListener {
        fun backToZooAreaDetail()
    }

    private var _vb: FragmentZooPlantDetailBinding? = null
    private val vb get() = _vb!!
    private val vm : ZooPlantDetailFragmentViewModel by viewModels()
    private val navArgs: ZooPlantDetailFragmentArgs by navArgs()
    private val gson by lazy { Gson() }
    private var listener: ZooPlantDetailFragmentListener? = null
    private val adapter by lazy { ContentItemAdapter() }

    init {
        lifecycleScope.launchWhenStarted {
            processNavArgs()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindLiveData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    private fun setupRecyclerView() {
        vb.rvZooPlantContent.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        vb.rvZooPlantContent.addItemDecoration(DividerItemDecoration(20,0, 20, 0))
        vb.rvZooPlantContent.adapter = adapter
    }

    private fun processNavArgs() {
        gson.fromJson(navArgs.zooPlant, ZooPlant::class.java)?.apply {
            vb.ivImg.load(imgUrl)
            vb.toolbar.title = title ?: nameEn
            vm.toContentItems(requireContext(), this)
        }
    }

    private fun bindLiveData() {
        vm.contentItems.observe(viewLifecycleOwner) { contentItems ->
            adapter.submitList(contentItems)
        }
    }

    private fun setListener() {
        vb.toolbar.setNavigationOnClickListener {
            listener?.backToZooAreaDetail()
        }
    }

    companion object {
        fun newInstance() = ZooPlantDetailFragment()
    }

}