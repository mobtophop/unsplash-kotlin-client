package com.example.unsplashclient.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.unsplashclient.databinding.ImagePreviewCardViewBinding

class ImagePreviewCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style: Int = 0
) : ConstraintLayout(
    context, attributeSet,
    style,
) {


    private val binding =
        ImagePreviewCardViewBinding.inflate(
            LayoutInflater.from(context), this, true,
        ).apply { this.view = this@ImagePreviewCardView }
}