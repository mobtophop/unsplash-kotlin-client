package com.example.unsplashclient.ui.main_fragment.main_list_adapter

import androidx.recyclerview.widget.DiffUtil

class MainListItemComparator : DiffUtil.ItemCallback<MainListItemData>() {
    override fun areItemsTheSame(
        oldItem: MainListItemData,
        newItem: MainListItemData
    ): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(
        oldItem: MainListItemData,
        newItem: MainListItemData
    ): Boolean {
        return oldItem == newItem
    }
}