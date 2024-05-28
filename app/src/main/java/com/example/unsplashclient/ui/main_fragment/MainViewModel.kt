package com.example.unsplashclient.ui.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashclient.ui.main_fragment.image_preview_adapter.BaseImagePreviewData
import com.example.unsplashclient.ui.main_fragment.image_preview_adapter.EmptyImagePreviewData
import com.example.unsplashclient.ui.main_fragment.quick_search_adapter.BaseQuickSearchData
import com.example.unsplashclient.ui.main_fragment.quick_search_adapter.EmptyQuickSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val listBaseSourceImagePreview = MutableStateFlow<List<BaseImagePreviewData>>(
        listOf(
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
        )
    )

    val listBaseSourceQuickSearch = MutableStateFlow<List<BaseQuickSearchData>>(
        listOf(
            EmptyQuickSearchData(Random.nextInt()),
            EmptyQuickSearchData(Random.nextInt()),
            EmptyQuickSearchData(Random.nextInt()),
            EmptyQuickSearchData(Random.nextInt()),
            EmptyQuickSearchData(Random.nextInt()),
            EmptyQuickSearchData(Random.nextInt()),
            EmptyQuickSearchData(Random.nextInt()),
            EmptyQuickSearchData(Random.nextInt()),
        )
    )
}