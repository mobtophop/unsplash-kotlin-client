package com.example.unsplashclient.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.unsplashclient.api.UnsplashApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UnsplashRepository @Inject
constructor(private val unsplashApiService: UnsplashApiService) {
    fun getImages(query: String?) =
        Pager(
            config = PagingConfig(
                10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashDataSource(unsplashApiService, query) }
        ).liveData

    suspend fun getPostInfo(id: String?) =
        UnsplashDataSource(unsplashApiService, null).getPostInfo(id)

}