package com.example.unsplashclient.data

import com.example.unsplashclient.api.UnsplashApiService
import javax.inject.Inject

class UnsplashDataSource @Inject constructor(private val unsplashApiService: UnsplashApiService) {
    suspend fun getLatestImages() =
        unsplashApiService.getLatestImages(page = "1", per_page = "10")
}