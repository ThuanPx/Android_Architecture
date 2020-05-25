package com.example.framgia.architecture.base.recyclerView

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by ThuanPx
 */

open class SuperDiffUtil<T>(
    var oldList: List<T> = mutableListOf(),
    var newList: List<T> = mutableListOf()
) : DiffUtil.Callback() {

    private var areContentsTheSame: ((T, T) -> Boolean)? = null
    private var areItemsTheSame: ((T, T) -> Boolean)? = null

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areItemsTheSame?.invoke(oldList[oldItemPosition], newList[newItemPosition])
            ?: (oldList[oldItemPosition] == newList[newItemPosition])

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areContentsTheSame?.invoke(oldList[oldItemPosition], newList[newItemPosition])
            ?: (oldList[oldItemPosition] == newList[newItemPosition])

    fun setData(old: List<T>, new: List<T>) {
        this.oldList = old
        this.newList = new
    }

    fun areContentsTheSame(function: (old: T, new: T) -> Boolean) {
        areContentsTheSame = function
    }

    fun areItemsTheSame(function: (old: T, new: T) -> Boolean) {
        areItemsTheSame = function
    }
}
