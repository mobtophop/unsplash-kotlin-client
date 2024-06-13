package com.example.unsplashclient.ui.image_view_fragment

//TODO: я скорее всего это позже использую
//import android.content.Context
//import android.util.AttributeSet
//import android.util.Log
//import android.view.MotionEvent
//import android.view.ScaleGestureDetector
//import android.view.View
//import android.widget.FrameLayout
//import androidx.core.view.children
//
//
//class CustomImageView : FrameLayout {
//    constructor(context: Context) : super(context)
//    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    )
//
//
//    init {
//
//        val scaleGestureDetector =
//            ScaleGestureDetector(context, MyImageViewScaleDetector(this.children.first()))
//
//
//        setOnTouchListener { view, event ->
//            Log.d(" :: TOUCH ACTION EVENT :: ", "mem")
//
//
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    Log.d(" :: ACTION_DOWN EVENT :: ", "kek")
//                    scaleGestureDetector.onTouchEvent(event)
//                }
//
//                MotionEvent.ACTION_UP -> view.performClick()
//                else -> {
//                    Log.d(" :: OTHER EVENT :: ", "kek")
//                    scaleGestureDetector.onTouchEvent(event)
//                }
//            }
//            true
//        }
//    }
//
//    inner class MyImageViewScaleDetector(val view: View) :
//        ScaleGestureDetector.OnScaleGestureListener {
//        override fun onScale(detector: ScaleGestureDetector): Boolean {
//            Log.d(" :: SCALE_EVENT ::", "${detector.scaleFactor}")
//            view.scaleX *= detector.scaleFactor
//            view.scaleY *= detector.scaleFactor
//
//            return true
//        }
//
//        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
//            Log.d(" :: SCALE_EVENT_BEGIN ::", "${detector.scaleFactor}")
//            return true
//
//        }
//
//        override fun onScaleEnd(detector: ScaleGestureDetector) {
//        }
//    }
//
//
//}