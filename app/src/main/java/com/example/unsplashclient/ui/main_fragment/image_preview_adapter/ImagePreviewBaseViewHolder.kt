package com.example.unsplashclient.ui.main_fragment.image_preview_adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ImagePreviewBaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}