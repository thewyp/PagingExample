package com.thewyp.pagingexample.data.model

sealed class UiModel {
    data class CatItem(val cat: Cat) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}
