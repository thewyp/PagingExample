package com.thewyp.pagingexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thewyp.pagingexample.databinding.CatLoadStateFooterBinding

class CatsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CatsLoadStateAdapter.LoadStateHolder>() {

    inner class LoadStateHolder(private val binding: CatLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                binding.progressBar.isVisible = loadState is LoadState.Loading
                binding.textViewError.isVisible = loadState is LoadState.Error
                binding.buttonRetry.isVisible = loadState is LoadState.Error
            }
        }

    }

    override fun onBindViewHolder(holder: LoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateHolder {
        return LoadStateHolder(
            CatLoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}