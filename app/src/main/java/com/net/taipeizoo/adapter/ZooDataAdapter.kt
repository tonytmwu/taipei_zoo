package com.net.taipeizoo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.net.taipeizoo.databinding.ViewContentItemBinding
import com.net.taipeizoo.databinding.ViewEmptyItemBinding
import com.net.taipeizoo.databinding.ViewItemBinding
import com.net.taipeizoo.fragment.ZooDetailListFragment.ZooAreaDetailType
import com.net.taipeizoo.model.ZooData

class ZooDataAdapter(
        private val listener: ZooDataViewListener? = null,
        private val zooDataType: ZooAreaDetailType? = null
): ListAdapter<ZooData, ZooDataAdapter.ViewHolder>(diffCallback) {

    interface ZooDataViewListener {
        fun onZooDataViewClick(zooDataType: ZooAreaDetailType?, data: ZooData, sharedElementView: View)
    }

    override fun getItemViewType(position: Int): Int {
        return with(getItem(position)) {
            if(contentItem != null) {
                CONTENT
            } else {
                when(this.rid == 0) {
                    true -> EMPTY
                    false -> DATA
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            EMPTY -> {
                val vb = ViewEmptyItemBinding.inflate(inflater, parent, false)
                EmptyViewHolder(vb)
            }
            CONTENT -> {
                val vb = ViewContentItemBinding.inflate(inflater, parent, false)
                ContentViewHolder(vb)
            }
            else -> {
                val vb = ViewItemBinding.inflate(inflater, parent, false)
                DataViewHolder(vb)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        open fun bind(data: ZooData) {}
    }

    private inner class EmptyViewHolder(vb: ViewEmptyItemBinding): ViewHolder(vb.root)

    private inner class DataViewHolder(private val vb: ViewItemBinding): ViewHolder(vb.root) {
        override fun bind(data: ZooData) {
            vb.tvName.text = data.title
            vb.tvCategory.text = data.category
            vb.tvInfo.text = data.info
            vb.ivImg.apply {
                ViewCompat.setTransitionName(this, data.imgUrl ?: "")
                load(data.imgUrl)
            }
            vb.root.setOnClickListener { listener?.onZooDataViewClick(zooDataType, data, vb.ivImg) }
        }
    }

    private inner class ContentViewHolder(private val vb: ViewContentItemBinding): ViewHolder(vb.root) {
        override fun bind(data: ZooData) {
            vb.tvTitle.text = data.contentItem?.title
            vb.tvContent.text = data.contentItem?.content
        }
    }

    companion object {
        const val EMPTY = 1
        const val DATA = 2
        const val CONTENT = 3

        val diffCallback = object: DiffUtil.ItemCallback<ZooData>() {
            override fun areItemsTheSame(oldItem: ZooData, newItem: ZooData): Boolean {
                return oldItem.rid == newItem.rid
            }

            override fun areContentsTheSame(oldItem: ZooData, newItem: ZooData): Boolean {
                return oldItem == newItem
            }
        }
    }

}