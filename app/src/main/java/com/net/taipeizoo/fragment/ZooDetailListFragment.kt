package com.net.taipeizoo.fragment

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.net.taipeizoo.CoreApplication
import com.net.taipeizoo.R
import com.net.taipeizoo.adapter.ZooDataAdapter
import com.net.taipeizoo.adapter.ZooDataAdapter.ZooDataViewListener
import com.net.taipeizoo.databinding.FragmentZooDetailListBinding
import com.net.taipeizoo.model.ZooArea
import com.net.taipeizoo.model.ZooData
import com.net.taipeizoo.view.DividerItemDecoration
import com.net.taipeizoo.viewmodel.ZooDetailListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class ZooDetailListFragment : Fragment(), ZooDataViewListener {

    interface ZooDetailListFragmentListener {
        fun showIntroduction(zooAreaDetailType: ZooAreaDetailType?, data: ZooData, sharedElementView: View)
    }

    @Parcelize
    enum class ZooAreaDetailType(val title: String): Parcelable {
        PLANT(CoreApplication.context.getString(R.string.plant)), ANIMAL(CoreApplication.context.getString(R.string.animal))
    }

    companion object {
        val ARG_ZOO_AREA = "arg_zoo_area"
        val ARG_ZOO_AREA_DETAIL_TYPE = "arg_zoo_area_detail_type"

        fun newInstance(zooArea: ZooArea?, type: ZooAreaDetailType?) = ZooDetailListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_ZOO_AREA, zooArea)
                putParcelable(ARG_ZOO_AREA_DETAIL_TYPE, type)
            }
        }
    }

    private var _vb: FragmentZooDetailListBinding? = null
    private val vb: FragmentZooDetailListBinding get() = _vb!!
    private val vm: ZooDetailListViewModel by viewModels()
    private var zooArea: ZooArea? = null
    private var zooAreaDetailType: ZooAreaDetailType? = null
    private lateinit var adapter: ZooDataAdapter
    var listener: ZooDetailListFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ZooDetailListFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        processArguments { zooArea, zooAreaDetailType ->
            initZooDataAdapter(zooAreaDetailType)
        }
        _vb = FragmentZooDetailListBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                zooArea?.let {
                    bindFlow(it)
                }
            }
        }
        return vb.root
    }

    private fun processArguments(callback: (zooArea: ZooArea?, zooAreaDetailType: ZooAreaDetailType?) -> Unit) {
        zooArea = arguments?.getParcelable(ARG_ZOO_AREA)
        zooAreaDetailType = arguments?.getParcelable(ARG_ZOO_AREA_DETAIL_TYPE)
        callback.invoke(zooArea, zooAreaDetailType)
    }

    private fun initZooDataAdapter(zooAreaDetailType: ZooAreaDetailType?) {
        adapter = ZooDataAdapter(this, zooAreaDetailType)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        vb.rvZooDetailList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        vb.rvZooDetailList.addItemDecoration(DividerItemDecoration(20,0, 20, 10))
        with(vb.rvZooDetailList) {
            adapter = this@ZooDetailListFragment.adapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        adapter.submitList(ZooData.mockData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    private suspend fun bindFlow(zooArea: ZooArea) {
        zooArea.title?.let { name ->
            when(zooAreaDetailType) {
                ZooAreaDetailType.ANIMAL -> vm.collectZooAreaAnimals(name).collect {
                    adapter.submitList(it)
                }
                ZooAreaDetailType.PLANT -> vm.collectZooAreaPlants(name).collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onZooDataViewClick(
        zooDataType: ZooAreaDetailType?,
        data: ZooData,
        sharedElementView: View
    ) {
        listener?.showIntroduction(zooDataType, data, sharedElementView)
    }

}