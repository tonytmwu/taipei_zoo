package com.net.taipeizoo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.net.taipeizoo.databinding.ViewZooAreaBinding
import com.net.taipeizoo.model.ZooArea

class ZooAreaAdapter: ListAdapter<ZooArea, ZooAreaAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vb = ViewZooAreaBinding.inflate(inflater, parent, false)
        return DataViewHolder(vb)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        open fun bind(data: ZooArea) {}
    }

    private inner class DataViewHolder(private val vb: ViewZooAreaBinding): ViewHolder(vb.root) {
        override fun bind(data: ZooArea) {
            vb.tvName.text = data.name
            vb.tvCategory.text = data.category
            vb.tvInfo.text = data.info
        }
    }

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<ZooArea>() {
            override fun areItemsTheSame(oldItem: ZooArea, newItem: ZooArea): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ZooArea, newItem: ZooArea): Boolean {
                return oldItem == newItem
            }
        }
    }

}