package com.example.framgia.architecture.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun androidx.fragment.app.Fragment.goTo(fragment: androidx.fragment.app.Fragment, frameId: Int, addToBackStack: Boolean = true) =
    childFragmentManager.transact {
        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        replace(frameId, fragment)
    }

fun androidx.fragment.app.Fragment.goBack() = childFragmentManager.popBackStackImmediate()

fun androidx.fragment.app.Fragment.addTo(fragment: androidx.fragment.app.Fragment, frameId: Int) =
    childFragmentManager.transact {
        add(frameId, fragment)
    }

inline fun <reified T: Any> androidx.fragment.app.Fragment.extra(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}
inline fun <reified T: Any> androidx.fragment.app.Fragment.extraNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

private inline fun androidx.fragment.app.FragmentManager.transact(action: androidx.fragment.app.FragmentTransaction.() -> Unit) =
    beginTransaction().apply { action() }.commit()
