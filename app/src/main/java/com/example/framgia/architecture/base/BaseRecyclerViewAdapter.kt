package com.example.framgia.architecture.base

/**
 *
 * Created by ThuanPx on 2/21/19.
 *
 */
interface BaseRecyclerViewAdapter<T> {

    fun setData(list: List<T>)

    fun setItem(item: T)

    fun removeItem(pos: Int)
}