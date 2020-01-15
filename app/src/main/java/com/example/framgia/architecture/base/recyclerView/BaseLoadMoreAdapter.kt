package mi.omiseno.handy.base.recyclerView

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.framgia.architecture.R
import com.example.framgia.architecture.base.recyclerView.BaseRecyclerViewAdapter
import com.example.framgia.architecture.utils.extension.inflate
import com.example.framgia.architecture.utils.extension.show
import kotlinx.android.synthetic.main.item_load_more.view.*

@Suppress("UNCHECKED_CAST")
abstract class BaseLoadMoreAdapter<T>() :
    BaseRecyclerViewAdapter<T, RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_PROGRESS = 0xFFFF
        const val TAG = "LoadMoreAdapter"
    }

    var isLoadMore = false

    @NonNull
    override fun getItemViewType(position: Int): Int {
        if (isLoadMore && position == bottomItemPosition()) {
            return TYPE_PROGRESS
        }
        return getItemViewTypeLM(position)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (TYPE_PROGRESS == viewType) {
            val view = parent.inflate(R.layout.item_load_more)
            return ItemLoadMoreViewHolder(view)
        }
        return onCreateViewHolderLM(parent, viewType)
    }

    @NonNull
    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= dataList.size) {
            return
        }
        if (TYPE_PROGRESS == holder.itemViewType) {
            (holder as ItemLoadMoreViewHolder).bind(isLoadMore)
            return
        }
        onBindViewHolderLM(holder, position)
    }

    fun bottomItemPosition(): Int {
        return itemCount - 1
    }

    fun onStartLoadMore() {
        handler.post {
            if (dataList.isEmpty() || isLoadMore) return@post
            isLoadMore = true

            dataList.add(null as T)
            notifyItemInserted(bottomItemPosition())
        }
    }

    fun onStopLoadMore() {
        handler.post {
            if (!isLoadMore) return@post
            isLoadMore = false
            if (dataList.size == 0) return@post
            dataList.removeAt(bottomItemPosition())
            notifyItemRemoved(dataList.size)
        }
    }

    class ItemLoadMoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(isLoadMore: Boolean) {
            itemView.loading.show(isLoadMore)
        }

    }

    protected abstract fun onCreateViewHolderLM(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder

    protected abstract fun onBindViewHolderLM(holder: RecyclerView.ViewHolder, position: Int)

    protected abstract fun getItemViewTypeLM(position: Int): Int
}