package com.example.unsplashclient.ui.main_fragment

import androidx.lifecycle.ViewModel
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.MainListItemData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.EmptyImagePreviewData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.QuickSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val mainListBaseSource = MutableStateFlow(
        listOf(
            QuickSearchData(Random.nextInt(), "", ""),
            QuickSearchData(Random.nextInt(), "", ""),
            QuickSearchData(Random.nextInt(), "", ""),
            QuickSearchData(Random.nextInt(), "", ""),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
        )
    )
}