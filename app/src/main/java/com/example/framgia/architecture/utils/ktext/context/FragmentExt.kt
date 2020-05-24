package com.example.framgia.architecture.utils.ktext.context

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.framgia.architecture.R
import com.thuanpx.ktext.AnimationType
import com.thuanpx.ktext.BOTTOM_UP
import com.thuanpx.ktext.FADE
import com.thuanpx.ktext.SLIDE_TO_LEFT
import com.thuanpx.ktext.SLIDE_TO_RIGHT

/**
 * Created by ThuanPx on 3/15/20.
 */

fun Fragment.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = true,
    tag: String = fragment::class.java.simpleName,
    @AnimationType animateType: Int = SLIDE_TO_LEFT
) {
    this.requireActivity().supportFragmentManager.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        replace(containerId, fragment, tag)
    }, animateType = animateType)
}

fun Fragment.addFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = true,
    tag: String = fragment::class.java.simpleName,
    @AnimationType animateType: Int = SLIDE_TO_LEFT
) {
    this.requireActivity().supportFragmentManager.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        add(containerId, fragment, tag)
    }, animateType = animateType)
}

fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
    this.apply { arguments = Bundle().apply(argsBuilder) }

/**
 * Runs a FragmentTransaction, then calls commitAllowingStateLoss().
 */
inline fun FragmentManager.transact(
    action: FragmentTransaction.() -> Unit,
    @AnimationType animateType: Int = SLIDE_TO_LEFT
) {
    beginTransaction().apply {
        setCustomAnimations(this, animateType)
        action()
    }.commitAllowingStateLoss()
}

fun setCustomAnimations(
    transaction: FragmentTransaction,
    @AnimationType animateType: Int = SLIDE_TO_LEFT
) {
    when (animateType) {
        FADE -> {
            transaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out)
        }
        SLIDE_TO_RIGHT -> {
            transaction.setCustomAnimations(
                R.anim.exit_to_left, R.anim.enter_from_right,
                R.anim.exit_to_right, R.anim.enter_from_left
            )
        }
        BOTTOM_UP -> {
            transaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out)
        }
        SLIDE_TO_LEFT -> {
            transaction.setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right
            )
        }
    }
}

/**
 * If no window token is found, keyboard is checked using reflection to know if keyboard visibility toggle is needed
 *
 * @param useReflection - whether to use reflection in case of no window token or not
 */
fun Fragment.hideKeyboard(useReflection: Boolean = true) {
    val windowToken = view?.rootView?.windowToken
    val imm = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    windowToken?.let {
        imm.hideSoftInputFromWindow(windowToken, 0)
    } ?: run {
        if (useReflection) {
            try {
                if (InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
                        .invoke(imm) as Int > 0
                ) {
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            } catch (exception: Exception) {
            }
        }
    }
}
