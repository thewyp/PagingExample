package com.thewyp.pagingexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thewyp.pagingexample.databinding.SeparatorViewItemBinding

class SeparatorViewHolder(private val binding: SeparatorViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(separatorText: String) {
        binding.separatorDescription.text = separatorText
    }

    companion object {
        fun create(parent: ViewGroup): SeparatorViewHolder {
            return SeparatorViewHolder(
                SeparatorViewItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent,false
                )
            )
        }
    }
}