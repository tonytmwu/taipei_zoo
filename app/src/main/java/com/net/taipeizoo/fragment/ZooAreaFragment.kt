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

class ZooAreaFragment : Fragment(), ZooDataAdapter.ZooDataViewListener {

    interface ZooAreaFragmentListener {
        fun showDetail(data: ZooData)
    }

    private var _vb: FragmentZooAreaBinding? = null
    private val vb get() = _vb!!

    private val vm: ZooAreaViewModel by viewModels()
    private val adapter by lazy { ZooDataAdapter(this) }
    private var listener: ZooAreaFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as? ZooAreaFragmentListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vb = FragmentZooAreaBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindLiveData()
        vm.fetchZoomAreas()
    }

    private fun bindLiveData() {
        vm.zooAreas.observe(viewLifecycleOwner) { zooAreas ->
            adapter.submitList(zooAreas)
        }
    }

    private fun setupRecyclerView() {
        vb.rvZooArea.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        vb.rvZooArea.addItemDecoration(DividerItemDecoration(20,0, 20, 10))
        vb.rvZooArea.adapter = adapter
    }

    override fun onZooDataViewClick(data: ZooData) {
        listener?.showDetail(data)
    }

    companion object {
        fun newInstance() = ZooAreaFragment()
    }

}