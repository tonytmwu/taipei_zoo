package com.net.taipeizoo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.net.taipeizoo.databinding.ViewContentItemBinding
import com.net.taipeizoo.model.ContentItem

class ContentItemAdapter(): ListAdapter<ContentItem, ContentItemAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vb = ViewContentItemBinding.inflate(inflater, parent, false)
        return ViewHolder(vb)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val vb: ViewContentItemBinding): RecyclerView.ViewHolder(vb.root) {
        fun bind(data: ContentItem) {
            vb.tvTitle.text = data.title
            vb.tvContent.text = data.content
        }
    }

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<ContentItem>() {
            override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}