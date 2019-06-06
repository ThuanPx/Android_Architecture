package com.example.framgia.android_architecture.utils.schedulers

import androidx.annotation.NonNull
import io.reactivex.Scheduler

interface SchedulerProvider {

    @NonNull
    fun computation(): Scheduler

    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler
}
