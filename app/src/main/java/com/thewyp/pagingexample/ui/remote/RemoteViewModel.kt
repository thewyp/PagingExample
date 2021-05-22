package com.thewyp.pagingexample.ui.remote

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.thewyp.pagingexample.data.Repository

class RemoteViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val cats = repository.getCatsFromNetwork().asLiveData()

}