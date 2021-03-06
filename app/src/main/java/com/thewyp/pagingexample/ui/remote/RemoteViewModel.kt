package com.thewyp.pagingexample.ui.remote

import com.thewyp.pagingexample.data.Repository
import com.thewyp.pagingexample.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RemoteViewModel @Inject constructor(
    repository: Repository
) : BaseViewModel() {

    override var dataSource = repository.getCatsFromNetwork()

}