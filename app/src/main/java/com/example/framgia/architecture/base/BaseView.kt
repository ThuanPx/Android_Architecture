package com.example.framgia.architecture.base

import com.example.framgia.architecture.utils.widget.dialogManager.DialogAlert
import com.example.framgia.architecture.utils.widget.dialogManager.DialogConfirm
import io.reactivex.disposables.Disposable


interface BaseView {

    fun showLoading(isShow: Boolean)
    fun showLoading()
    fun hideLoading()
    fun handleApiError(apiError: Throwable)
    fun launchRx(job: () -> Disposable)

    fun showAlertDialog(
        title: String = "", message: String = "", titleButton: String = "",
        listener: DialogAlert.Companion.OnButtonClickedListener? = null
    )

    fun showConfirmDialog(
            title: String? = "", message: String? = "",
            titleButtonPositive: String = "", titleButtonNegative: String = "",
            listener: DialogConfirm.OnButtonClickedListener? = null, isPayment: Boolean? = null
    )
}
