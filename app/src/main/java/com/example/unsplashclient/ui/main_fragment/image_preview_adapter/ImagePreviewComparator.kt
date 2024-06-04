package com.example.unsplashclient.ui.main_fragment.image_preview_adapter

import androidx.recyclerview.widget.DiffUtil

class ImagePreviewComparator : DiffUtil.ItemCallback<ImagePreviewData>() {
    override fun areItemsTheSame(
        oldItem: ImagePreviewData,
        newItem: ImagePreviewData
    ): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(
        oldItem: ImagePreviewData,
        newItem: ImagePreviewData
    ): Boolean {
        return oldItem == newItem
    }
}