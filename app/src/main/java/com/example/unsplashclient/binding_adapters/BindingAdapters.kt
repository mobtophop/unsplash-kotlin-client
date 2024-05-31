package com.example.unsplashclient.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.unsplashclient.extencions.loading

@BindingAdapter("image", "color")
fun ImageView.setImage(imageUrl: String, color: String?) {
    this.loading(imageUrl, color)
}