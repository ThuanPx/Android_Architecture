package com.example.framgia.android_architecture.data.source.local

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Hyperion on 15/12/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class SharedPrefs(context: Context) {
    private val mSharedPreferences: SharedPreferences
    var accessToken: String
        set(value) = put(SharedPrefs.PREF_ACCESS_TOKEN, value)
        get() = get(SharedPrefs.PREF_ACCESS_TOKEN, String::class.java)

    init {
        mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> mSharedPreferences.getString(key, "")
            Boolean::class.java -> mSharedPreferences.getBoolean(key, false)
            Float::class.java -> mSharedPreferences.getFloat(key, -1f)
            Double::class.java -> mSharedPreferences.getFloat(key, -1f)
            Int::class.java -> mSharedPreferences.getInt(key, -1)
            Long::class.java -> mSharedPreferences.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = mSharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

    companion object {
        val PREFS_NAME = "Android Architecture"
        private val PREFIX = "Architecture_"
        val PREF_ACCESS_TOKEN = PREFIX + "access_token"
    }
}
