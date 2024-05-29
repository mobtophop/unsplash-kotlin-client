package com.example.unsplashclient.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class UnsplashClientApplication @Inject constructor() : Application()