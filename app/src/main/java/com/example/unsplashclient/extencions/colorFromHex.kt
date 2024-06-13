package com.example.unsplashclient.extencions

import android.graphics.Color
import android.widget.FrameLayout

fun FrameLayout.colorFromHex(color: String?) {
    color?.let {
        val newColor = Color.parseColor(color)
        this.setBackgroundColor(newColor)
    }
}