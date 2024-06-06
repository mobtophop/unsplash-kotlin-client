package com.example.unsplashclient.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplashclient.api.UnsplashApiService
import com.example.unsplashclient.api.UnsplashPhotoData
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UnsplashDataSource @Inject constructor(
    private val unsplashApiService: UnsplashApiService,
    private val query: String?
) :
    PagingSource<Int, UnsplashPhotoData>() {
    override fun getRefreshKey(state: PagingState<Int, UnsplashPhotoData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhotoData> {

        return try {
            val pageNumber = params.key ?: 0
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null

            val result: List<UnsplashPhotoData>? =
                if (query?.isEmpty() != false) unsplashApiService.getLatestImages(
                    page = "$pageNumber",
                    per_page = "10"
                ).body() else unsplashApiService.getSearchImages(
                    page = "$pageNumber",
                    per_page = "10",
                    query = query
                ).body()?.results

            val nextKey = if ((result ?: listOf()).isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = result ?: listOf(),
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