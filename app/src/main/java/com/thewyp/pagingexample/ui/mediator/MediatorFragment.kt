package com.thewyp.pagingexample.ui.mediator

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.paging.LoadState
import com.thewyp.pagingexample.R
import com.thewyp.pagingexample.databinding.FragmentRemoteBinding
import com.thewyp.pagingexample.ui.CatsAdapter
import com.thewyp.pagingexample.ui.CatsLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediatorFragment : Fragment(R.layout.fragment_remote) {

    private lateinit var binding: FragmentRemoteBinding

    private val viewModel by viewModels<MediatorViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRemoteBinding.bind(view)
        val adapter = CatsAdapter()

        binding.apply {
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CatsLoadStateAdapter { adapter.retry() },
                footer = CatsLoadStateAdapter { adapter.retry() }
            )
        }

        viewModel.cats.asLiveData().observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.mediator?.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.mediator?.refresh is LoadState.Error
                textViewError.isVisible = loadState.mediator?.refresh is LoadState.Error

                buttonRetry.setOnClickListener {
                    adapter.retry()
                }

                // empty view
                if (loadState.mediator?.refresh is LoadState.NotLoading &&
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