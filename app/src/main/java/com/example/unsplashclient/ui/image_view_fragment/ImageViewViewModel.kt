package com.example.unsplashclient.ui.image_view_fragment

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.unsplashclient.ui.main_fragment.image_preview_adapter.ImagePreviewData
import com.example.unsplashclient.use_cases.UnsplashUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel(assistedFactory = ImageViewViewModel.ImageViewViewModelFactory::class)
class ImageViewViewModel @AssistedInject constructor(
    private val unsplashUseCase: UnsplashUseCase,
    @Assisted state: SavedStateHandle
) : ViewModel() {
    @AssistedFactory
    interface ImageViewViewModelFactory {
        fun create(state: SavedStateHandle): ImageViewViewModel
    }

    suspend fun getPostInfo(id: String): ImagePreviewData? {
        return unsplashUseCase.getPostInfo(id)
    }
}