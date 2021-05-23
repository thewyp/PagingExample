package com.thewyp.pagingexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.thewyp.pagingexample.R
import com.thewyp.pagingexample.data.model.Cat
import com.thewyp.pagingexample.data.model.UiModel
import com.thewyp.pagingexample.databinding.ItemCatBinding

class CatsAdapter :
    PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<UiModel>() {
        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return (oldItem is UiModel.CatItem && newItem is UiModel.CatItem
                    && oldItem.cat.id == newItem.cat.id)
                    || (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem
                    && oldItem.description == newItem.description)
        }

    }) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.CatItem -> R.layout.item_cat
            is UiModel.SeparatorItem -> R.layout.separator_view_item
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.item_cat)
            CatsHolder.create(parent) else SeparatorViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            when (uiModel) {
                is UiModel.CatItem -> (holder as CatsHolder).bind(uiModel.cat)
                is UiModel.SeparatorItem -> (holder as SeparatorViewHolder).bind(uiModel.description)
            }
        }
    }
}