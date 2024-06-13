package com.example.unsplashclient.binding_adapters


import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.unsplashclient.extencions.loading
import com.example.unsplashclient.extencions.scaling
import com.example.unsplashclient.extencions.colorFromHex

@BindingAdapter("image", "color")
fun ImageView.setImage(imageUrl: String, color: String?) {
    this.loading(imageUrl, color)
}

@BindingAdapter("scalable")
fun ImageView.scalable(scale: Boolean?) {
    this.scaling(scale)
}

@BindingAdapter("colorFromHex")
fun FrameLayout.colorFromHex(color: String?) {
    this.colorFromHex(color)
}