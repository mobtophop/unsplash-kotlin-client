package com.example.unsplashclient.ui.main_fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashclient.data.UnsplashRepository
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.MainListItemData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.EmptyImagePreviewData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.ImagePreviewData
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.QuickSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) : ViewModel() {
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

    init {
        getLatestImages()
    }

    private fun getLatestImages() = viewModelScope.launch {

        val res = unsplashRepository.getLatestImages().body()

        res?.let { result ->
            mainListBaseSource.update { list ->
                val newList = list.filter { item ->
                    item !is EmptyImagePreviewData
                }.toMutableList()

                result.forEach { resultItem ->
                    newList.add(
                        ImagePreviewData(
                            id = resultItem.id ?: "",
                            imageUrl = resultItem.urls.regular ?: "",
                            color = resultItem.color ?: "#000000",
                            authorName = resultItem.user.name ?: "",
                            authorPfp = resultItem.user.profile_image.medium ?: "",
                        )
                    )
                }

                newList
            }
        }
    }
}