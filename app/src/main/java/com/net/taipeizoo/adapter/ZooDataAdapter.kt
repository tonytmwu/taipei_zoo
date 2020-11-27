package com.net.taipeizoo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.net.taipeizoo.databinding.ViewItemBinding
import com.net.taipeizoo.model.ZooData

class ZooDataAdapter(
        private val listener: ZooDataViewListener? = null
): ListAdapter<ZooData, ZooDataAdapter.ViewHolder>(diffCallback) {

    interface ZooDataViewListener {
        fun onZooDataViewClick(data: ZooData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vb = ViewItemBinding.inflate(inflater, parent, false)
        return DataViewHolder(vb)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        open fun bind(data: ZooData) {}
    }

    private inner class DataViewHolder(private val vb: ViewItemBinding): ViewHolder(vb.root) {
        override fun bind(data: ZooData) {
            vb.tvName.text = data.title
            vb.tvCategory.text = data.category
            vb.tvInfo.text = data.info
            vb.ivImg.load(data.imgUrl)
            vb.root.setOnClickListener { listener?.onZooDataViewClick(data) }
        }
    }

    companion object {
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