package com.example.unsplashclient.api

import com.example.unsplashclient.misc.UNSPLASH_ACCESS_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    @Headers("Authorization: Client-ID $UNSPLASH_ACCESS_KEY")
    suspend fun getLatestImages(
        @Query("page") page: String?,
        @Query("per_page") per_page: String?,
    ): Response<UnsplashResponse>
}