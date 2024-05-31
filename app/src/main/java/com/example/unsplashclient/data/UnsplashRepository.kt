package com.example.unsplashclient.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UnsplashRepository @Inject
constructor(private val remoteDataSource: UnsplashDataSource) {

    suspend fun getLatestImages() =
        remoteDataSource.getLatestImages()
}