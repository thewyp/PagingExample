package com.thewyp.pagingexample.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thewyp.pagingexample.R
import com.thewyp.pagingexample.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentMainBinding.bind(view)

        binding.apply {
            networkOnly.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_remoteFragment)
            }
        }
    }
}