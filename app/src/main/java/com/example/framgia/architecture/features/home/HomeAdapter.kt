package com.example.framgia.architecture.features.home

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.recyclerView.BaseRecyclerViewAdapter
import com.example.framgia.architecture.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 *
 * Created by ThuanPx on 1/28/19.
 *
 */
class HomeAdapter(private val itemClickListenerInvoker: (Int) -> Unit) : BaseRecyclerViewAdapter<User, HomeAdapter.Companion.ItemViewHolder>() {

    fun submitData(list: MutableList<User>) {
        val diffUtil = UserDiffUtil(getData(), list)
        updateData(list, diffUtil)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_user, p0, false)
        return ItemViewHolder(view, itemClickListenerInvoker)
    }

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        p0.onBind(getData()[p1], p1)
    }

    companion object {
        class ItemViewHolder(view: View, private val itemClickListener: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
            fun onBind(user: User, pos: Int) {
                with(itemView) {
                    Glide.with(context)
                            .load(user.avatarUrl)
                            .apply(RequestOptions.circleCropTransform())
                            .into(ivUserImage)
                    tvUserName.text = user.id.toString() + "-----" + user.login
                    setOnClickListener { itemClickListener.invoke(pos) }
                }
            }
        }
    }
}

private class UserDiffUtil(@NonNull private var olds: List<User>, @NonNull private val news: List<User>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return olds[p0].id == news[p1].id
    }

    override fun getOldListSize(): Int = olds.size

    override fun getNewListSize(): Int = news.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return olds[p0] == news[p1]
    }
}
