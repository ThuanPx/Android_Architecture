package com.example.framgia.android_architecture.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
fun Fragment.goTo(fragment: Fragment, frameId: Int, addToBackStack: Boolean = true) =
    childFragmentManager.transact {
        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        replace(frameId, fragment)
    }

fun Fragment.goBack() = childFragmentManager.popBackStackImmediate()

fun Fragment.addTo(fragment: Fragment, frameId: Int) =
    childFragmentManager.transact {
        add(frameId, fragment)
    }

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) =
    beginTransaction().apply { action() }.commit()
