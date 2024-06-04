package com.example.unsplashclient.ui.main_fragment.quick_search_adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class QuickSearchViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}