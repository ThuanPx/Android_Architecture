package com.example.framgia.architecture.utils.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.framgia.architecture.Constants
import com.example.framgia.architecture.Constants.EXTRA_ARGS
import kotlin.reflect.KClass

/**
 * Various extension functions for AppCompatActivity.
 */

fun <T : Activity> FragmentActivity.startActivity(cls: KClass<T>, bundle: Bundle? = null,
                                          parcel: Parcelable? = null) {
    intent = Intent(this, cls.java)
    if (bundle != null) intent.putExtra(EXTRA_ARGS, bundle)
    if (parcel != null) intent.putExtra(EXTRA_ARGS, parcel)
    startActivity(intent)
}

fun FragmentActivity.startActivityForResult(@NonNull intent: Intent,
                                            requestCode: Int, flags: Int? = null) {
    flags?.let {
        intent.flags = it
    }
    startActivityForResult(intent, requestCode)
}

fun FragmentActivity.startActivityAtRoot(@NonNull clazz: KClass<out Activity>,
                                         args: Bundle? = null) {
    val intent = Intent(this, clazz.java)
    args?.let {
        intent.putExtras(it)
    }
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    this.startActivity(intent)
}

fun FragmentActivity.replaceFragmentInActivity(@IdRes containerId: Int, fragment: Fragment,
                                                addToBackStack: Boolean = true, tag: String = fragment::class.java.simpleName,
                                                animateType: Int = Constants.AnimationType.FADE) {
    supportFragmentManager.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        replace(containerId, fragment, tag)
    }, animateType = animateType)
}

fun FragmentActivity.addFragmentToActivity(@IdRes containerId: Int, fragment: Fragment,
                                            addToBackStack: Boolean = true, tag: String = fragment::class.java.simpleName,
                                            animateType: Int = Constants.AnimationType.FADE) {
    supportFragmentManager.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        add(containerId, fragment, tag)
    }, animateType = animateType)
}

fun FragmentActivity.goBackFragment(): Boolean {
    val isShowPreviousPage = supportFragmentManager.backStackEntryCount > 0
    if (isShowPreviousPage) {
        supportFragmentManager.popBackStackImmediate()
    }
    return isShowPreviousPage
}

fun FragmentActivity.isVisibleFragment(tag: String): Boolean? {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    return fragment?.isAdded ?: false && fragment?.isVisible ?: false
}

inline fun <reified T: Any> FragmentActivity.getFragmentInActivity(clazz: KClass<T>): T? {
    val tag = clazz.java.simpleName
    return supportFragmentManager.findFragmentByTag(tag) as T?
}

/**
 * val test = extra<String>("test")
 * */
inline fun <reified T : Any> androidx.fragment.app.FragmentActivity.extra(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else default
}
