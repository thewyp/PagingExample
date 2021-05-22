package com.thewyp.pagingexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.thewyp.pagingexample.R
import com.thewyp.pagingexample.data.model.Cat
import com.thewyp.pagingexample.databinding.ItemCatBinding

class CatsAdapter :
    PagingDataAdapter<Cat, CatsAdapter.CatsHolder>(object : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsHolder {
        return CatsHolder(
            ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CatsHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cat?) {
            binding.imageCat.load(item?.url) {
                placeholder(R.drawable.ic_launcher_background)
            }
        }

    }
}