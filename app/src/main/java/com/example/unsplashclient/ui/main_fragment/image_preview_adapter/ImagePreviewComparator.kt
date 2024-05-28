package com.example.unsplashclient.ui.main_fragment.image_preview_adapter

import androidx.recyclerview.widget.DiffUtil

class ImagePreviewComparator : DiffUtil.ItemCallback<BaseImagePreviewData>() {
    override fun areItemsTheSame(
        oldItem: BaseImagePreviewData,
        newItem: BaseImagePreviewData
    ): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(
        oldItem: BaseImagePreviewData,
        newItem: BaseImagePreviewData
    ): Boolean {
        return oldItem == newItem
    }
}