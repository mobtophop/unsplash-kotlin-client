package com.example.unsplashclient.data

import com.example.unsplashclient.api.UnsplashResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class UnsplashRepository @Inject
constructor(private val remoteDataSource: UnsplashDataSource) {

    suspend fun getLatestImages(): Response<UnsplashResponse> =
        remoteDataSource.getLatestImages()
}