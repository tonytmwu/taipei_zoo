package com.net.taipeizoo.fragment

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.gson.Gson
import com.net.taipeizoo.adapter.ContentItemAdapter
import com.net.taipeizoo.databinding.FragmentIntroductionBinding
import com.net.taipeizoo.model.ZooAnimal
import com.net.taipeizoo.model.ZooPlant
import com.net.taipeizoo.view.DividerItemDecoration
import com.net.taipeizoo.viewmodel.IntroductionViewModel

class IntroductionFragment : Fragment() {

    interface ZooPlantDetailFragmentListener {
        fun backToZooAreaDetail()
    }

    private var _vb: FragmentIntroductionBinding? = null
    private val vb get() = _vb!!
    private val vm : IntroductionViewModel by viewModels()
    private val gson by lazy { Gson() }
    private var listener: ZooPlantDetailFragmentListener? = null
    private val adapter by lazy { ContentItemAdapter() }
    private var uiTransitionName: String? = null

    init {
        lifecycleScope.launchWhenStarted {
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
    ): View {
        _vb = FragmentIntroductionBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArgs()
        ViewCompat.setTransitionName(vb.ivImg, uiTransitionName ?: "")
        setupRecyclerView()
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

    private fun processArgs() {
        arguments?.let {
            val zooDataType = it.getString(EXTRA_ZOO_DATA_TYPE)
            val data = it.getString(EXTRA_DATA)

            when(zooDataType) {
                ZooDetailListFragment.ZooAreaDetailType.ANIMAL.name -> {
                    gson.fromJson(data, ZooAnimal::class.java)?.apply {
                        setZooData(title ?: nameEn, imgUrl)
                        vm.toContentItems(requireContext(), this)
                    }
                }
                ZooDetailListFragment.ZooAreaDetailType.PLANT.name -> {
                    gson.fromJson(data, ZooPlant::class.java)?.apply {
                        setZooData(title ?: nameEn, imgUrl)
                        vm.toContentItems(requireContext(), this)
                    }
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun setZooData(title: String?, imgUrl: String?) {
        uiTransitionName = imgUrl
        vb.ivImg.load(imgUrl)
        vb.toolbar.title = title
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
        val EXTRA_ZOO_DATA_TYPE = "zooDataType"
        val EXTRA_DATA = "data"

        fun newInstance(zooDataType: String?, data: String?) = IntroductionFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_ZOO_DATA_TYPE, zooDataType)
                putString(EXTRA_DATA, data)
            }
        }
    }

}