package com.example.unsplashclient.extencions

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import kotlin.math.pow
import kotlin.math.roundToInt

fun FrameLayout.scaleChild(scale: Boolean?) {

    if (scale != true) return

    val scaleGestureDetector =
        ScaleGestureDetector(context, MyImageViewScaleDetector(children.first(), this))

    val gestureDetector =
        GestureDetector(context, MyImageViewGestureDetector(children.first(), this))

    setOnTouchListener { view, event ->


        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (event.pointerCount == 1) gestureDetector.onTouchEvent(event)
            }

            MotionEvent.ACTION_UP -> view.performClick()
            else -> {
                when (event.pointerCount) {
                    1 -> gestureDetector.onTouchEvent(event)
                    else -> scaleGestureDetector.onTouchEvent(event)
                }
            }
        }
        true
    }
}

class MyImageViewScaleDetector(private val childView: View, private val parentView: View) :
    ScaleGestureDetector.OnScaleGestureListener {

    private var startOffsetX = 0F
    private var startOffsetY = 0F

    private var lastOffsetX = 0F
    private var lastOffsetY = 0F

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        val scaleFactor = detector.scaleFactor.toDouble().pow(2F.toDouble()).toFloat()

        childView.scaleX *= scaleFactor
        childView.scaleY *= scaleFactor

        val deltaOffsetX = startOffsetX - detector.focusX
        val deltaOffsetY = startOffsetY - detector.focusY


        val param = childView.layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(
            (lastOffsetX - deltaOffsetX * scaleFactor * 2).roundToInt(),
            (lastOffsetY - deltaOffsetY * scaleFactor * 2).roundToInt(),
            0,
            0,
        )
        childView.layoutParams = param

        return true
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        startOffsetX = detector.focusX
        startOffsetY = detector.focusY

        lastOffsetX = childView.marginLeft.toFloat()
        lastOffsetY = childView.marginTop.toFloat()
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
    }
}


class MyImageViewGestureDetector(private val childView: View, private val parentView: View) :
    GestureDetector.OnGestureListener {

    private var lastOffsetX = 0F
    private var lastOffsetY = 0F

    private var startEvent = true

    override fun onDown(e: MotionEvent): Boolean {

        startEvent = true
        return true
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        // fixes "jump" at the start of the gesture
        if (startEvent) {
            startEvent = false
            return true
        }

        val param = childView.layoutParams as ViewGroup.MarginLayoutParams

        val horizontalOffset = (lastOffsetX - distanceX).roundToInt()
        val verticalOffset = (lastOffsetY - distanceY).roundToInt()
        lastOffsetX = horizontalOffset.toFloat()
        lastOffsetY = verticalOffset.toFloat()

        param.setMargins(
            horizontalOffset,
            verticalOffset,
            -horizontalOffset,
            -verticalOffset,
        )
        childView.layoutParams = param

        return true
    }

    override fun onLongPress(e: MotionEvent) {
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }

}

