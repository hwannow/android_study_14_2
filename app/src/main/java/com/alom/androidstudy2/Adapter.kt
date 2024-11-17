package com.alom.androidstudy2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alom.androidstudy2.data.Item
import com.alom.androidstudy2.databinding.ItemSampleBinding
import com.bumptech.glide.Glide

class Adapter: ListAdapter<Item, Adapter.ItemViewHolder>(diffUtil) {
    inner class ItemViewHolder(private val binding: ItemSampleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(itemModel: Item) {
            binding.tvTitle.text = itemModel.title
            binding.tvPrice.text = itemModel.price
            binding.tvTime.text = itemModel.time
            Glide
                .with(binding.image.context)
                .load(itemModel.imageUrl)
                .into(binding.image)
        }
    }

    companion object {
        val diffUtil = object:DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}