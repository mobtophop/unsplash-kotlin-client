package com.example.unsplashclient.di

import com.example.unsplashclient.api.UnsplashApiService
import com.example.unsplashclient.misc.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.unsplash.com"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(logging)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): UnsplashApiService =
        retrofit.create(UnsplashApiService::class.java)

    private val json by lazy {
        Json {
            encodeDefaults = false
            ignoreUnknownKeys = true
            isLenient = true
            allowSpecialFloatingPointValues = false
            allowStructuredMapKeys = true
            prettyPrint = false
            coerceInputValues = true
            useArrayPolymorphism = false
        }
    }
}
