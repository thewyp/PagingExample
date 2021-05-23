package com.thewyp.pagingexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.thewyp.pagingexample.R
import com.thewyp.pagingexample.data.model.Cat
import com.thewyp.pagingexample.databinding.ItemCatBinding

class CatsHolder(private val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Cat?) {
        binding.imageCat.load(item?.url) {
            placeholder(R.drawable.ic_launcher_background)
        }
//        binding.index.text = item?.id + ", $bindingAdapterPosition"
    }

    companion object {
        fun create(parent: ViewGroup): CatsHolder {
            return CatsHolder(
                ItemCatBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}