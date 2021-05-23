package com.thewyp.pagingexample.ui.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thewyp.pagingexample.api.CatApi
import com.thewyp.pagingexample.data.model.Cat
import java.lang.Exception

const val STARTING_PAGE_INDEX = 1

class CatsPagingSource(private val catApi: CatApi) : PagingSource<Int, Cat>() {

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = catApi.getCatImages(size = params.loadSize, page = page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}