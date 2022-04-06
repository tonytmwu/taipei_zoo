package com.net.taipeizoo.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.net.taipeizoo.adapter.ZooDataAdapter
import com.net.taipeizoo.databinding.FragmentZooAreaBinding
import com.net.taipeizoo.model.ZooData
import com.net.taipeizoo.view.DividerItemDecoration
import com.net.taipeizoo.viewmodel.ZooAreaFragmentViewModel

class ZooAreaFragment : Fragment(), ZooDataAdapter.ZooDataViewListener {

    interface ZooAreaFragmentListener {
        fun showDetail(data: ZooData, sharedElementView: View)
    }

    private var _vb: FragmentZooAreaBinding? = null
    private val vb get() = _vb!!

    private val vm: ZooAreaFragmentViewModel by viewModels()
    private val adapter by lazy { ZooDataAdapter(this) }
    private var listener: ZooAreaFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as? ZooAreaFragmentListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentZooAreaBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bindLiveData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    private fun bindLiveData() {
        vm.zooAreas.observe(viewLifecycleOwner) { zooAreas ->
            adapter.submitList(zooAreas)
            vb.shimmerLoading.hideShimmer()
        }
    }

    private fun setupRecyclerView() {
        vb.rvZooArea.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        vb.rvZooArea.addItemDecoration(DividerItemDecoration(20,0, 20, 10))
        with(vb.rvZooArea) {
            adapter = this@ZooAreaFragment.adapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        adapter.submitList(ZooData.mockData)
    }

    override fun onZooDataViewClick(
        zooDataType: ZooDetailListFragment.ZooAreaDetailType?,
        data: ZooData,
        sharedElementView: View
    ) {
        listener?.showDetail(data, sharedElementView)
    }

    companion object {
        fun newInstance() = ZooAreaFragment()
    }
}