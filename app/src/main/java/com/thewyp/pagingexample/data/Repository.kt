package com.thewyp.pagingexample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thewyp.pagingexample.api.CatApi
import com.thewyp.pagingexample.data.db.CatDatabase
import com.thewyp.pagingexample.data.model.Cat
import com.thewyp.pagingexample.ui.mediator.CatRemoteMediator
import com.thewyp.pagingexample.ui.remote.CatsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


private const val PAGE_SIZE = 15

class Repository @Inject constructor(private val api: CatApi, private val database: CatDatabase) {

    fun getCatsFromDb(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
//                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { database.getCatDao().getAll() }
        ).flow
    }

    fun getCatsFromNetwork() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
//            maxSize = PAGE_SIZE + PAGE_SIZE * 3,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE * 1,
            prefetchDistance = 1

        ),
        pagingSourceFactory = {
            CatsPagingSource(api)
        }
    ).flow

    @ExperimentalPagingApi
    fun getCatsFromRemoteMediator() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
//            maxSize = PAGE_SIZE + PAGE_SIZE * 3,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE * 1,
            prefetchDistance = 1
        ),
        remoteMediator = CatRemoteMediator(api, database),
        pagingSourceFactory = { database.getCatDao().getAll() }
    ).flow

}