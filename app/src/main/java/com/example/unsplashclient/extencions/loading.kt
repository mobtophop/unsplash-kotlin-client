package com.example.unsplashclient.extencions


import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.unsplashclient.R


fun ImageView.loading(iconUrl: String, color: String?) {
    color?.let {
        val newColor = Color.parseColor(color)
        this.setBackgroundColor(newColor)
    }

    Glide.with(this@loading)
        .load(iconUrl)
        .error(R.color.light_blue_400)
        .fallback(R.color.light_blue_400)
        .into(this)
}
