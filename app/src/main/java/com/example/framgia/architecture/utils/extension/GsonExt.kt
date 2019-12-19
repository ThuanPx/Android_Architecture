package com.example.framgia.architecture.utils.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * --------------------
 * Created by ThuanPx on 6/10/2019.
 * Screen name:
 * --------------------
 */

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)