package com.example.framgia.android_architecture.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

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

fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
    this.apply { arguments = Bundle().apply(argsBuilder) }

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) =
    beginTransaction().apply { action() }.commit()
