package com.example.framgia.architecture.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.framgia.architecture.Constants.EXTRA_ARGS
import kotlin.reflect.KClass

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */

/**
 *
 *  this.goTo(HomeActivity::class) {
 *  val bundle = Bundle().apply {
 *  putString("test","ts")
 *  }
 *  putExtra("test",bundle)
 *  }
 */
fun <T : Activity> FragmentActivity.goTo(cls: KClass<T>, initBundle: Intent.() -> Unit = {}) {
    intent = Intent(this, cls.java)
    intent.initBundle()
    startActivity(intent)
}

fun <T : Activity> FragmentActivity.goForResult(cls: KClass<T>, requestCode: Int,
                                                initBundle: Intent.() -> Unit = {}) {
    intent = Intent(this, cls.java)
    intent.initBundle()
    startActivityForResult(intent, requestCode)
}

fun FragmentActivity.goBack() = finish()

fun <T : Activity> FragmentActivity.rootTo(cls: KClass<T>, bundle: Bundle? = null) {
    intent = Intent(this, cls.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    if (bundle != null) intent.putExtra(EXTRA_ARGS, bundle)
    startActivity(intent)
}

fun FragmentActivity.goTo(fragment: Fragment, frameId: Int, addToBackStack: Boolean = true) =
    supportFragmentManager.transact {
        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        replace(frameId, fragment)
    }

fun FragmentActivity.addTo(fragment: Fragment, tag: String) =
    supportFragmentManager.transact {
        add(fragment, tag)
    }

/**
 * val test = extra<String>("test")
 * */
inline fun <reified T : Any> FragmentActivity.extra(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> FragmentActivity.extraNotNull(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}


private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) =
    beginTransaction().apply { action() }.commit()
