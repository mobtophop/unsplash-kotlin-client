package com.example.unsplashclient.ui.main_fragment.main_list_adapter

sealed class MainListItemData

data class EmptyImagePreviewData(val id: Int) : MainListItemData()

data class ImagePreviewData(
    val id: String,
    val imageUrl: String,
    val color: String,
    val authorName: String,
    val authorPfp: String,
) : MainListItemData()

data class QuickSearchData(
    val id: String,
    val imageUrl: String,
    val label: String,
) : MainListItemData()
