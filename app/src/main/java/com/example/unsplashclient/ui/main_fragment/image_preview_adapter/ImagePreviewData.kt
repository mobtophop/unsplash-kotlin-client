package com.example.unsplashclient.ui.main_fragment.image_preview_adapter

sealed class BaseImagePreviewData

data class EmptyImagePreviewData(val id: Int) : BaseImagePreviewData()

data class ImagePreviewData(
    val id: Int,
    val imageUrl: String,
    val authorName: String,
    val authorPfp: String,
) : BaseImagePreviewData()

