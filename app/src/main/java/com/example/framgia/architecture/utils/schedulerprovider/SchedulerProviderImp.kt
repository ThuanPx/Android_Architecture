package com.example.framgia.architecture.utils.schedulerprovider

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */
class SchedulerProviderImp: SchedulerProvider  {
    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()
}