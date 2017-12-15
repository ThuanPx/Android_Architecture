package com.example.framgia.android_architecture.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.framgia.android_architecture.Constants.EXTRA_ARGS
import kotlin.reflect.KClass

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun <T : Activity> FragmentActivity.goTo(cls: KClass<T>, bundle: Bundle? = null,
    parcel: Parcelable? = null) {
    intent = Intent(this, cls.java)
    if (bundle != null) intent.putExtra(EXTRA_ARGS, bundle)
    if (parcel != null) intent.putExtra(EXTRA_ARGS, bundle)
    startActivity(intent)
}

fun <T : Activity> FragmentActivity.goForResult(cls: KClass<T>, requestCode: Int,
    bundle: Bundle? = null,
    parcel: Parcelable? = null) {
    intent = Intent(this, cls.java)
    if (bundle != null) intent.putExtra(EXTRA_ARGS, bundle)
    if (parcel != null) intent.putExtra(EXTRA_ARGS, parcel)
    startActivityForResult(intent, requestCode)
}

fun FragmentActivity.goBack() = finish()

fun <T : Activity> FragmentActivity.rootTo(cls: KClass<T>, bundle: Bundle? = null) {
    intent = Intent(this, cls.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    if (bundle != null) intent.putExtra(EXTRA_ARGS, bundle)
    startActivity(intent)
}

fun FragmentActivity.goTo(fragment: Fragment, frameId: Int) =
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }

fun FragmentActivity.addTo(fragment: Fragment, tag: String) =
    supportFragmentManager.transact {
        add(fragment, tag)
    }

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) =
    beginTransaction().apply { action() }.commit()
