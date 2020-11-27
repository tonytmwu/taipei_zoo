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
import kotlinx.coroutines.launch

class ZooAreaDetailFragment : Fragment(), ZooDataAdapter.ZooDataViewListener {

    interface ZooAreaDetailFragmentListener {
        fun backToZooArea()
        fun showZooPlantDetail(data: ZooPlant)
    }

    private var _vb: FragmentZooAreaDetailBinding? = null
    private val vb get() = _vb!!
    private val vm: ZooAreaDetailViewModel by viewModels()
    private val navArgs: ZooAreaDetailFragmentArgs by navArgs()
    private val gson by lazy { Gson() }
    private var zooArea: ZooArea? = null
    private val adapter by lazy { ZooDataAdapter(this) }
    private var listener: ZooAreaDetailFragmentListener? = null

    init {
        lifecycleScope.launchWhenStarted {
            processNavArgs()
            setListener()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
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
    }

    private fun processNavArgs() {
        gson.fromJson(navArgs.zooArea, ZooArea::class.java)?.apply {
            zooArea = this
            setZooAreaDetailInfo(zooArea)
            name?.let { vm.fetchZooPlants(name)}
        }
    }

    private fun setZooAreaDetailInfo(zooArea: ZooArea?) {
        vb.ivImg.load(zooArea?.imgUrl)
        vb.toolbar.title = zooArea?.name
        vb.tvInfo.text = zooArea?.info
        vb.tvCategory.text = zooArea?.category
    }

    private fun setupRecyclerView() {
        vb.rvZooAreaDetail.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        vb.rvZooAreaDetail.addItemDecoration(DividerItemDecoration(20,0, 20, 10))
        vb.rvZooAreaDetail.adapter = adapter
    }

    private fun bindLiveData() {
        vm.zooPlants.observe(viewLifecycleOwner) { zooPlants ->
            println("zooPlants = ${zooPlants?.size}")
            adapter.submitList(zooPlants)
        }
    }

    private fun setListener() {
        vb.toolbar.setNavigationOnClickListener {
            listener?.backToZooArea()
        }
    }

    override fun onZooDataViewClick(data: ZooData) {
        lifecycleScope.launch {
            vm.getZooPlant(data.id)?.let { selectedZooPlant ->
                listener?.showZooPlantDetail(selectedZooPlant)
            }
        }
    }

    companion object {
        fun newInstance() = ZooAreaDetailFragment()
    }

}