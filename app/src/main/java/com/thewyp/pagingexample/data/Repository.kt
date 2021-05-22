package com.thewyp.pagingexample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.thewyp.pagingexample.api.CatApi
import com.thewyp.pagingexample.ui.remote.CatsPagingSource
import javax.inject.Inject


private const val PAGE_SIZE = 30

class Repository @Inject constructor(private val api: CatApi) {

    fun getCatsFromNetwork() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
//            maxSize = PAGE_SIZE + PAGE_SIZE * 3,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            CatsPagingSource(api)
        }
    ).flow

}