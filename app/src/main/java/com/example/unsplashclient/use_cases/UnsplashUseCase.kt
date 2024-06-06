package com.example.unsplashclient.use_cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import com.example.unsplashclient.data.UnsplashRepository
import com.example.unsplashclient.ui.main_fragment.image_preview_adapter.ImagePreviewData
import javax.inject.Inject

class UnsplashUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    fun getImages(query: String?):
            LiveData<PagingData<ImagePreviewData>> {
        return unsplashRepository.getImages(query).map {
            it.map { data ->
                ImagePreviewData(
                    id = data.id ?: "",
                    imageUrl = data.urls.regular ?: "",
                    color = data.color ?: "#000000",
                    authorName = data.user.name ?: "",
                    authorPfp = data.user.profile_image.medium ?: "",
                )
            }
        }
    }
}