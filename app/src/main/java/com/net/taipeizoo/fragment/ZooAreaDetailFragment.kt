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
import com.net.taipeizoo.adapter.ZooDataAdapter
import com.net.taipeizoo.databinding.FragmentZooAreaDetailBinding
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.model.ZooData
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.view.DividerItemDecoration
import com.net.taipeizoo.viewmodel.ZooAreaDetailFragmentViewModel
import kotlinx.coroutines.launch

class ZooAreaDetailFragment : Fragment(), ZooDataAdapter.ZooDataViewListener {

    interface ZooAreaDetailFragmentListener {
        fun backToZooArea()
        fun showZooPlantDetail(data: ZooPlant)
    }

    private var _vb: FragmentZooAreaDetailBinding? = null
    private val vb get() = _vb!!
    private val vm: ZooAreaDetailFragmentViewModel by viewModels()
    private val navArgs: ZooAreaDetailFragmentArgs by navArgs()
    private val gson by lazy { Gson() }
    private var zooArea: ZooArea? = null
    private val adapter by lazy { ZooDataAdapter(this) }
    private var listener: ZooAreaDetailFragmentListener? = null

    init {
        lifecycleScope.launchWhenResumed {
            processNavArgs()
            setListener()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _vb = FragmentZooAreaDetailBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ZooAreaDetailFragmentListener
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

    override fun onResume() {
        super.onResume()
        /**
         * 因為pop backStack或是旋轉螢幕，CoordinatorLayout不會記得之前的狀態，所以在onResume在塞一次資料到畫面上
         * google issue tracker - https://issuetracker.google.com/issues/37055789#c8
         */
        setZooAreaDetailInfo(zooArea)
        setToolbarListener()
    }

    private fun processNavArgs() {
        gson.fromJson(navArgs.zooArea, ZooArea::class.java)?.apply {
            zooArea = this
            setZooAreaDetailInfo(zooArea)
            title?.let { vm.startObserveZooPlants(it, category, info) }
        }
    }

    private fun setZooAreaDetailInfo(zooArea: ZooArea?) {
        vb.ivImg.load(zooArea?.imgUrl)
        vb.toolbar.title = zooArea?.title
    }

    private fun setupRecyclerView() {
        vb.rvZooPlant.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        vb.rvZooPlant.addItemDecoration(DividerItemDecoration(20,0, 20, 10))
        vb.rvZooPlant.adapter = adapter
        adapter.submitList(ZooData.mockData)
    }

    private fun setListener() {
        setToolbarListener()
    }

    private fun bindLiveData() {
        vm.zooPlants.observe(viewLifecycleOwner) { zooPlants ->
            adapter.submitList(zooPlants)
        }
    }

    private fun setToolbarListener() {
        vb.toolbar.setNavigationOnClickListener {
            listener?.backToZooArea()
        }
    }

    override fun onZooDataViewClick(data: ZooData) {
        lifecycleScope.launch {
            vm.getZooPlant(data.rid, vm.zooPlants.value)?.let { selectedZooPlant ->
                listener?.showZooPlantDetail(selectedZooPlant)
            }
        }
    }

    companion object {
        fun newInstance() = ZooAreaDetailFragment()
    }

}