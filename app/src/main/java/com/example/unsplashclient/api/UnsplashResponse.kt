package com.example.unsplashclient.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashResponse(
    @SerializedName("data")
    var data: List<UnsplashPhotoData>,
)

@Serializable
data class UnsplashPhotoData(
    @SerializedName("id")
    var id: String?,
    var color: String?,
    var urls: List<String>?,
)