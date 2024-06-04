package com.example.unsplashclient.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplashclient.api.UnsplashApiService
import com.example.unsplashclient.api.UnsplashPhotoData
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UnsplashDataSource @Inject constructor(private val unsplashApiService: UnsplashApiService) :
    PagingSource<Int, UnsplashPhotoData>() {
//    suspend fun getLatestImages() =
//        unsplashApiService.getLatestImages(page = "1", per_page = "10")

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhotoData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhotoData> {

        return try {
            val pageNumber = params.key ?: 0


            val response = unsplashApiService.getLatestImages(page = "$pageNumber", per_page = "10")

            val prevKey = if (pageNumber > 0) pageNumber - 1 else null

            val nextKey = if ((response.body() ?: listOf()).isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = response.body() ?: listOf(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}