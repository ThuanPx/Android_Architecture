package com.example.framgia.architecture.features.home

import android.annotation.SuppressLint
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
 * Created by ThuanPx on 1/28/19.
 */
class HomeAdapter : BaseRecyclerViewAdapter<User, HomeAdapter.Companion.ItemViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_user, p0, false)
        return ItemViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }

    override fun getDiffUtil() = super.getDiffUtil().apply {
        areItemsTheSame { old, new ->
            old.id == new.id
        }
    }

    companion object {
        class ItemViewHolder(view: View,
                             private val itemClickListener: ((User?, Int?) -> Unit)?) : RecyclerView.ViewHolder(view) {
            @SuppressLint("SetTextI18n")
            fun onBind(user: User?, pos: Int) {
                with(itemView) {
                    Glide.with(context)
                        .load(user?.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivUserImage)
                    tvUserName.text = user?.id.toString() + "-----" + user?.login
                    setOnClickListener { itemClickListener?.invoke(user, pos) }
                }
            }
        }
    }
}
