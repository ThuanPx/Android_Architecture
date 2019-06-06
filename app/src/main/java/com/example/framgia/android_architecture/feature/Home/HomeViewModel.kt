package com.example.framgia.android_architecture.feature.Home

import com.example.framgia.android_architecture.base.BaseViewModel
import com.example.framgia.android_architecture.data.source.repository.RepoRepository
import com.example.framgia.android_architecture.utils.async
import com.example.framgia.android_architecture.utils.schedulers.SchedulerProvider
import javax.inject.Inject

/**
 * --------------------
 * Created by ThuanPx on 6/6/2019.
 * Screen name:
 * --------------------
 */
class HomeViewModel @Inject constructor(private val repoRepository: RepoRepository, private val schedulerProvider: SchedulerProvider) : BaseViewModel() {

    fun searchRepo(key: String, page: Int) {
        launchRx {
            repoRepository.searchRepo(key, page)
                    .async(schedulerProvider)
                    .subscribe({

                    }, {

                    })
        }
    }
}