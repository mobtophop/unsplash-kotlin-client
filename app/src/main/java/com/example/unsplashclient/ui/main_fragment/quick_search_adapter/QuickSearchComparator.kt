package com.example.unsplashclient.ui.main_fragment.quick_search_adapter

import androidx.recyclerview.widget.DiffUtil

class QuickSearchComparator : DiffUtil.ItemCallback<BaseQuickSearchData>() {
    override fun areItemsTheSame(
        oldItem: BaseQuickSearchData,
        newItem: BaseQuickSearchData
    ): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(
        oldItem: BaseQuickSearchData,
        newItem: BaseQuickSearchData
    ): Boolean {
        return oldItem == newItem
    }
}