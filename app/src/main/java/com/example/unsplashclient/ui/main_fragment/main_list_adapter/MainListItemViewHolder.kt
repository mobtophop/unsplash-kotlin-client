package com.example.unsplashclient.ui.main_fragment.main_list_adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MainListItemViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}