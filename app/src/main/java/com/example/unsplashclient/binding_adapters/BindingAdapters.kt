package com.example.unsplashclient.binding_adapters


import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.unsplashclient.extencions.loading
import com.example.unsplashclient.extencions.colorFromHex
import com.example.unsplashclient.extencions.scaleChild

@BindingAdapter("image", "color")
fun ImageView.setImage(imageUrl: String, color: String?) {
    this.loading(imageUrl, color)
}

@BindingAdapter("scaleChild")
fun FrameLayout.scaleChild(scale: Boolean?) {
    this.scaleChild(scale)
}

@BindingAdapter("colorFromHex")
fun FrameLayout.colorFromHex(color: String?) {
    this.colorFromHex(color)
}