package com.thewyp.pagingexample.ui.remote

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.thewyp.pagingexample.R
import com.thewyp.pagingexample.databinding.FragmentRemoteBinding
import com.thewyp.pagingexample.ui.CatsAdapter
import com.thewyp.pagingexample.ui.CatsLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemoteFragment : Fragment(R.layout.fragment_remote) {

    private lateinit var binding: FragmentRemoteBinding

    private val viewModel: RemoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRemoteBinding.bind(view)

        val adapter = CatsAdapter()

        binding.apply {
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CatsLoadStateAdapter { adapter.retry() },
                footer = CatsLoadStateAdapter { adapter.retry() }
            )
        }

        viewModel.cats.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }
}