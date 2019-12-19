package com.example.framgia.architecture.data.source.remote.middleware

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class UnauthorizedBroadcast(private val mContext: Context,
    val lifecycle: Lifecycle) : LifecycleObserver {
    private val localBM by lazy { LocalBroadcastManager.getInstance(mContext) }

    private val mBroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
               //TODO when unauthorized
            }
        }
    }

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun register() {
        localBM.registerReceiver(mBroadcastReceiver, IntentFilter(INTENT_UNAUTHORIZED))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun unregister() {
        localBM.unregisterReceiver(mBroadcastReceiver)
    }

    companion object {
        const val INTENT_UNAUTHORIZED = "action_unauthorized"
    }
}
