package com.example.framgia.android_architecture.utils

import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.framgia.android_architecture.R

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun View.showSnackBar(snackBarText: String, timeLength: Int = 3000,
    actionTextRes: Int? = null, onActionClickListener: View.OnClickListener? = null) =
    Snackbar.make(this, snackBarText, timeLength).apply {
        if (actionTextRes != null && onActionClickListener != null) {
            setAction(actionTextRes, onActionClickListener)
            val tvAction = view.findViewById<TextView>(android.support.design.R.id.snackbar_action)
            tvAction?.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            tvAction?.setAllCaps(true)
        }
    }.show()

fun View.showError(error: Throwable, timeLength: Int = 3000,
    actionTextRes: Int? = null, onActionClickListener: View.OnClickListener? = null) {
    showSnackBar(error.message ?: "", timeLength, actionTextRes, onActionClickListener)
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

fun View.visible(isVisible: Boolean) = if (isVisible) this.show() else this.hide()

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
