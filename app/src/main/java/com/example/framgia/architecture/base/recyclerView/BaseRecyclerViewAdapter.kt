package com.example.framgia.architecture.base.recyclerView

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ThuanPx on 3/15/20.
 * Base Adapter.
 *
 * @param <V> is a type extend from {@link RecyclerView.ViewHolder}
 * @param <T> is a Object
 */

abstract class BaseRecyclerViewAdapter<T, V : RecyclerView.ViewHolder>
constructor(
    protected var dataList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<V>() {

    protected var itemClickListener: ((T?, Int?) -> Unit)? = null

    protected var handler = Handler(Looper.getMainLooper())

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getData(): MutableList<T> {
        return dataList
    }

    fun updateData(newData: MutableList<T>?, diffUtilCallback: DiffUtil.Callback) {
        handler.post {
            newData?.let {
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                dataList.clear()
                dataList.addAll(it)
                diffResult.dispatchUpdatesTo(this)
            }
        }
    }

    fun updateData(newData: MutableList<T>?) {
        handler.post {
            newData?.let {
                dataList.addAll(it)
                notifyDataSetChanged()
            }
        }
    }

    fun replaceData(newData: MutableList<T>?) {
        handler.post {
            newData?.let {
                dataList = it
                notifyDataSetChanged()
            }
        }
    }

    fun clearData(isNotify: Boolean = true) {
        dataList.clear()
        if (isNotify) notifyDataSetChanged()
    }

    open fun getItem(position: Int): T? {
        return if (position < 0 || position >= dataList.size) {
            null
        } else dataList[position]
    }

    fun addItem(data: T, position: Int) {
        dataList.add(position, data)
        notifyItemInserted(position)
    }

    fun removeItem(position: Int, isNotifyAll: Boolean = false) {
        if (position < 0 || position >= dataList.size) {
            return
        }
        dataList.removeAt(position)
        if (isNotifyAll) notifyDataSetChanged() else notifyItemChanged(position)
    }

    fun replaceItem(item: T, position: Int, isNotifyAll: Boolean = false) {
        if (position < 0 || position >= dataList.size) {
            return
        }
        dataList[position] = item
        if (isNotifyAll) notifyDataSetChanged() else notifyItemChanged(position)
    }

    fun setOnItemClickListener(onItemClickListener: (T?, Int?) -> Unit) {
        itemClickListener = onItemClickListener
    }

    fun unRegisterItemClickListener() {
        itemClickListener = null
    }

    fun submitData(newData: MutableList<T>?) {
        newData?.let {
            updateData(newData, diffUtilCallback = getDiffUtil().apply { setData(old = getData(), new = newData) })
        }
    }

    open fun getDiffUtil(): SuperDiffUtil<T> {
        return SuperDiffUtil()
    }
}
