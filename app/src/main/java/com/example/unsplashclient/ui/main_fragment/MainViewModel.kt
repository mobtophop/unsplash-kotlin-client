package com.example.unsplashclient.ui.main_fragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.unsplashclient.R
import com.example.unsplashclient.ui.main_fragment.quick_search_adapter.QuickSearchData
import com.example.unsplashclient.use_cases.UnsplashUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

@HiltViewModel(assistedFactory = MainViewModel.MainViewModelFactory::class)
class MainViewModel @AssistedInject constructor(
    private val unsplashUseCase: UnsplashUseCase,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface MainViewModelFactory {
        fun create(state: SavedStateHandle): MainViewModel
    }

    private val currentQuery = state.getLiveData("current_query", "")

    val photos = currentQuery.switchMap { queryString ->
        unsplashUseCase.getImages(queryString).cachedIn(viewModelScope)
    }

    val quickSearchBaseSource = MutableStateFlow(
        listOf(
            QuickSearchData(
                Random.nextInt().toString(),
                R.drawable.three_d,
                "3D",
                searchCallback = { setNewSearchQuery("3d") }),
            QuickSearchData(
                Random.nextInt().toString(),
                R.drawable.layer,
                "Textures",
                searchCallback = { setNewSearchQuery("textures") }),
            QuickSearchData(
                Random.nextInt().toString(),
                R.drawable.nature,
                "Nature",
                searchCallback = { setNewSearchQuery("nature") }),
            QuickSearchData(
                Random.nextInt().toString(),
                R.drawable.food,
                "Food",
                searchCallback = { setNewSearchQuery("food") }),
            QuickSearchData(
                Random.nextInt().toString(),
                R.drawable.travel,
                "Travel",
                searchCallback = { setNewSearchQuery("travel") }),
            QuickSearchData(
                Random.nextInt().toString(),
                R.drawable.elephant,
                "Animals",
                searchCallback = { setNewSearchQuery("animals") }),
        )
    )

    private fun setNewSearchQuery(query: String) {
        currentQuery.value = query
    }
}