package com.example.framgia.architecture.features

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.framgia.architecture.R
import com.example.framgia.architecture.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 *
 * Created by ThuanPx on 1/28/19.
 *
 */
class HomeAdapter(private val itemClickListener: (User) -> Unit) : RecyclerView.Adapter<HomeAdapter.Companion.ItemViewHolder>() {
    private val users by lazy { mutableListOf<User>() }

    fun set(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_user, p0, false)
        return ItemViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        p0.onBind(users[p1])
    }

    companion object {
        class ItemViewHolder(view: View, private val itemClickListener: (User) -> Unit) : RecyclerView.ViewHolder(view) {
            fun onBind(user: User) {
                with(itemView) {
                    Glide.with(context)
                        .load(user.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivUserImage)
                    tvUserName.text = user.login
                    setOnClickListener { itemClickListener.invoke(user) }
                }
            }
        }
    }
}