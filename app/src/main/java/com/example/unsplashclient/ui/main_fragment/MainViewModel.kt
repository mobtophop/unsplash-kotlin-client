package com.example.unsplashclient.ui.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.unsplashclient.R
import com.example.unsplashclient.ui.main_fragment.quick_search_adapter.QuickSearchData
import com.example.unsplashclient.use_cases.UnsplashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val unsplashUseCase: UnsplashUseCase
) : ViewModel() {

    val photos = unsplashUseCase.getLatestImages().cachedIn(viewModelScope)

    val quickSearchBaseSource = MutableStateFlow(
        listOf(
            QuickSearchData(Random.nextInt().toString(), R.drawable.three_d, "3D"),
            QuickSearchData(Random.nextInt().toString(), R.drawable.layer, "Textures"),
            QuickSearchData(Random.nextInt().toString(), R.drawable.nature, "Nature"),
            QuickSearchData(Random.nextInt().toString(), R.drawable.food, "Food"),
            QuickSearchData(Random.nextInt().toString(), R.drawable.travel, "Travel"),
            QuickSearchData(Random.nextInt().toString(), R.drawable.elephant, "Animals"),
        )
    )
}