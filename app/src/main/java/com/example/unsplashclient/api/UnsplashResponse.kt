package com.example.unsplashclient.api

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashSearchResult(
    var results: List<UnsplashPhotoData>,
)

@Serializable
data class UnsplashPhotoData(
    var id: String?,
    var color: String?,
    var urls: UnsplashPhotoLinks,
    var user: UnsplashUserInfo,
)

@Serializable
data class UnsplashPhotoLinks(
    var raw: String?,
    var full: String?,
    var regular: String?,
    var small: String?,
    var thumb: String?,
)

@Serializable
data class UnsplashUserInfo(
    var id: String?,
    var name: String?,
    var profile_image: UnsplashUserPfp,
)

@Serializable
data class UnsplashUserPfp(
    var small: String?,
    var medium: String?,
    var large: String?,
)