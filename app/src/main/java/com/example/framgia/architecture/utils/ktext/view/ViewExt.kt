package com.example.framgia.architecture.utils.ktext.view

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View

/**
 * Created by ThuanPx
 */

fun View.show(isShow: Boolean = true) {
    visibility = if (isShow) View.VISIBLE else View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible(isVisible: Boolean) = if (isVisible) this.show() else this.hide()

fun View.OnClickListener.listenToViews(vararg views: View) {
    views.forEach { it.setOnClickListener(this) }
}

@SuppressLint("ClickableViewAccessibility")
fun View.setOnVeryLongClickListener(timeDelay: Long = 3000, listener: () -> Unit) {
    setOnTouchListener(object : View.OnTouchListener {
        private val handler = Handler(Looper.getMainLooper())
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            if (event?.action == MotionEvent.ACTION_DOWN) {
                handler.postDelayed({
                    listener.invoke()
                }, timeDelay)
            } else if (event?.action == MotionEvent.ACTION_UP) {
                handler.removeCallbacksAndMessages(null)
            }
            return true
        }
    })
}
