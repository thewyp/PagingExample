package com.thewyp.pagingexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.thewyp.pagingexample.data.model.Cat
import com.thewyp.pagingexample.data.model.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class BaseViewModel : ViewModel() {

    abstract var dataSource: Flow<PagingData<Cat>>

    val cats: Flow<PagingData<UiModel>> by lazy {
//        dataSource.map { pagingData ->
//            pagingData.map {
//                UiModel.CatItem(it)
//            }
//        }
        dataSource
            .map { pagingData -> pagingData.map { UiModel.CatItem(it) } }
            .map {
                it.insertSeparators { before, after ->
                    if (after == null) {
                        // we're at the end of the list
                        return@insertSeparators null
                    }
                    val nameOfAfterItem = after.cat.id.first().toString()

                    if (before == null) {
                        // we're at the beginning of the list
                        return@insertSeparators UiModel.SeparatorItem(nameOfAfterItem)
                    }

                    // check between 2 items
                    val nameOfBeforeItem = before.cat.id.first().toString()
                    if (nameOfBeforeItem != nameOfAfterItem) {
                        return@insertSeparators UiModel.SeparatorItem(nameOfAfterItem)
                    } else {
                        // no separator
                        null
                    }
                }
            }.cachedIn(viewModelScope)
    }
}