package com.thewyp.pagingexample.ui.local

import com.thewyp.pagingexample.data.Repository
import com.thewyp.pagingexample.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    override var dataSource = repository.getCatsFromDb()

}