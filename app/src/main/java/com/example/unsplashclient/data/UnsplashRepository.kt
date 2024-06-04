package com.example.unsplashclient.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UnsplashRepository @Inject
constructor(private val remoteDataSource: UnsplashDataSource) {
    fun getLatestImages() =
        Pager(
            config = PagingConfig(
                10,
                enablePlaceholders = false

            ),
            pagingSourceFactory = { remoteDataSource }
        ).liveData

//    suspend fun getLatestImages() =
//        remoteDataSource.getLatestImages()
}