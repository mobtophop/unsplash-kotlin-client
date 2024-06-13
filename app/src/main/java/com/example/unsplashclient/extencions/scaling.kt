package com.example.unsplashclient.extencions

import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import kotlin.math.roundToInt

class MyImageViewScaleDetector(val view: View) : ScaleGestureDetector.OnScaleGestureListener {
    override fun onScale(detector: ScaleGestureDetector): Boolean {
        view.scaleX *= detector.scaleFactor
        view.scaleY *= detector.scaleFactor


        return true
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        return true

    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
    }
}

fun ImageView.scaling(scale: Boolean?) {

    if (scale != true) return

    val scaleGestureDetector =
        ScaleGestureDetector(context, MyImageViewScaleDetector(this))


    setOnTouchListener { view, event ->


        when (event.action) {
            MotionEvent.ACTION_DOWN -> {}

            MotionEvent.ACTION_UP -> view.performClick()
            else -> {
                scaleGestureDetector.onTouchEvent(event)
            }
        }
        true
    }
}

