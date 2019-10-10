package com.example.framgia.architecture.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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
fun <T : Activity> androidx.fragment.app.FragmentActivity.goTo(cls: KClass<T>, initBundle: Intent.() -> Unit = {}) {
    intent = Intent(this, cls.java)
    intent.initBundle()
    startActivity(intent)
}

fun <T : Activity> androidx.fragment.app.FragmentActivity.goForResult(cls: KClass<T>, requestCode: Int,
                                                                      initBundle: Intent.() -> Unit = {}) {
    intent = Intent(this, cls.java)
    intent.initBundle()
    startActivityForResult(intent, requestCode)
}

fun androidx.fragment.app.FragmentActivity.goBack() = finish()

fun <T : Activity> androidx.fragment.app.FragmentActivity.rootTo(cls: KClass<T>, bundle: Bundle? = null) {
    intent = Intent(this, cls.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    if (bundle != null) intent.putExtra(EXTRA_ARGS, bundle)
    startActivity(intent)
}

fun androidx.fragment.app.FragmentActivity.goTo(fragment: androidx.fragment.app.Fragment, frameId: Int, addToBackStack: Boolean = true) =
    supportFragmentManager.transact {
        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        replace(frameId, fragment)
    }

fun androidx.fragment.app.FragmentActivity.addTo(fragment: androidx.fragment.app.Fragment, tag: String) =
    supportFragmentManager.transact {
        add(fragment, tag)
    }

/**
 * val test = extra<String>("test")
 * */
inline fun <reified T : Any> androidx.fragment.app.FragmentActivity.extra(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> androidx.fragment.app.FragmentActivity.extraNotNull(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}


private inline fun androidx.fragment.app.FragmentManager.transact(action: androidx.fragment.app.FragmentTransaction.() -> Unit) =
    beginTransaction().apply { action() }.commit()
