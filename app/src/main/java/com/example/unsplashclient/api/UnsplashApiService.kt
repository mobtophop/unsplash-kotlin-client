package com.example.unsplashclient.api

import com.example.unsplashclient.misc.UNSPLASH_ACCESS_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    @Headers("Authorization: Client-ID $UNSPLASH_ACCESS_KEY")
    suspend fun getLatestImages(
        @Query("page") page: String?, // Paging 3
        @Query("per_page") per_page: String?,
    ): Response<List<UnsplashPhotoData>>

    @GET("search/photos")
    @Headers("Authorization: Client-ID $UNSPLASH_ACCESS_KEY")
    suspend fun getSearchImages(
        @Query("query") query: String?,
        @Query("page") page: String?,
        @Query("per_page") per_page: String?,
    ): Response<UnsplashSearchResult>

    @GET("/photos/{id}")
    @Headers("Authorization: Client-ID $UNSPLASH_ACCESS_KEY")
    suspend fun getPostInfo(
        @Path("id") id: String?,
    ): Response<UnsplashPhotoData>
}