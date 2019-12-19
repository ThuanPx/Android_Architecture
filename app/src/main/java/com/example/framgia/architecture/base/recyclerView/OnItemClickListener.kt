package com.example.framgia.architecture.base.recyclerView

import com.example.framgia.architecture.Constants


/**
 * OnItemClickListener
 *
 * @param <T> Data from item click
</T> */

interface OnItemClickListener<T> {
  fun onItemViewClick(item: T, position: Int = Constants.POSITION_DEFAULT)
}