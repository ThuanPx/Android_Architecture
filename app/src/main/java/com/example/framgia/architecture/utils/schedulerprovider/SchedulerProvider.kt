package com.example.framgia.architecture.utils.schedulerprovider

import io.reactivex.rxjava3.core.Scheduler

/**
 *
 * Created by ThuanPx on 1/25/19.
 *
 */
interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
