package com.example.unsplashclient.ui.main_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.unsplashclient.api.UnsplashPhotoData
import com.example.unsplashclient.data.UnsplashRepository
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.MainListItemData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.EmptyImagePreviewData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.ImagePreviewData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.QuickSearchData
import com.example.unsplashclient.use_cases.UnsplashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val unsplashUseCase: UnsplashUseCase
) : ViewModel() {

    val photos = unsplashUseCase.getLatestImages().cachedIn(viewModelScope)

    val mainListBaseSource = MutableStateFlow(
        listOf(
            QuickSearchData(Random.nextInt().toString(), "", ""),
            QuickSearchData(Random.nextInt().toString(), "", ""),
            QuickSearchData(Random.nextInt().toString(), "", ""),
            QuickSearchData(Random.nextInt().toString(), "", ""),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
            EmptyImagePreviewData(Random.nextInt()),
        )
    )
}