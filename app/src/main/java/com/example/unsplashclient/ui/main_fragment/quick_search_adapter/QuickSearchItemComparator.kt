package com.example.unsplashclient.ui.main_fragment.quick_search_adapter

import androidx.recyclerview.widget.DiffUtil

class QuickSearchItemComparator : DiffUtil.ItemCallback<QuickSearchData>() {
    override fun areItemsTheSame(
        oldItem: QuickSearchData,
        newItem: QuickSearchData
    ): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(
        oldItem: QuickSearchData,
        newItem: QuickSearchData
    ): Boolean {
        return oldItem == newItem
    }
}