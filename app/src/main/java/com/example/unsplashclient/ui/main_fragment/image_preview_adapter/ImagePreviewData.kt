package com.example.unsplashclient.ui.main_fragment.image_preview_adapter

data class ImagePreviewData(
    val id: String,
    val imageUrl: String,
    val fullImageUrl: String,
    val color: String,
    val authorName: String,
    val authorPfp: String,
)