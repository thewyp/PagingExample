package com.thewyp.pagingexample.ui.mediator

import androidx.paging.ExperimentalPagingApi
import com.thewyp.pagingexample.data.Repository
import com.thewyp.pagingexample.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediatorViewModel @Inject constructor(repository: Repository) : BaseViewModel() {
    @ExperimentalPagingApi
    override var dataSource = repository.getCatsFromRemoteMediator()
}