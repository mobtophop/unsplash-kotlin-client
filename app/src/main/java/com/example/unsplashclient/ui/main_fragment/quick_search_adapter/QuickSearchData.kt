package com.example.unsplashclient.ui.main_fragment.quick_search_adapter

sealed class BaseQuickSearchData

data class EmptyQuickSearchData(val id: Int) : BaseQuickSearchData()

data class QuickSearchData(
    val id: Int,
    val imageUrl: String,
    val label: String,
) : BaseQuickSearchData()

