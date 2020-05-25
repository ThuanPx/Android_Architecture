package com.example.framgia.architecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.framgia.architecture.data.source.remote.error.RetrofitException
import com.example.framgia.architecture.utils.widget.dialogManager.DialogAlert
import com.example.framgia.architecture.utils.widget.dialogManager.DialogConfirm
import com.example.framgia.architecture.utils.widget.dialogManager.DialogManager
import com.example.framgia.architecture.utils.widget.dialogManager.DialogManagerImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.net.HttpURLConnection
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by ThuanPx on 1/28/19.
 */
abstract class BaseActivity<VM : BaseViewModel>(clazz: KClass<VM>) : AppCompatActivity(), BaseView {

    protected abstract val layoutID: Int
    protected val viewModel: VM by viewModel(clazz)

    private lateinit var dialogManager: DialogManager
    private val compositeDisposable = CompositeDisposable()

    protected abstract fun initialize()
    protected abstract fun onSubscribeObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID)
        dialogManager = DialogManagerImpl(this)
        initialize()
        onSubscribeObserver()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun showLoading(isShow: Boolean) {
        if (isShow) showLoading() else hideLoading()
    }

    override fun showLoading() {
        dialogManager.showLoading()
    }

    override fun hideLoading() {
        dialogManager.hideLoading()
    }

    override fun showAlertDialog(
        title: String,
        message: String,
        titleButton: String,
        listener: DialogAlert.Companion.OnButtonClickedListener?
    ) {
        dialogManager.showAlertDialog(title, message, titleButton, listener)
    }

    override fun showConfirmDialog(
        title: String?,
        message: String?,
        titleButtonPositive: String,
        titleButtonNegative: String,
        listener: DialogConfirm.OnButtonClickedListener?,
        isPayment: Boolean?
    ) {
        dialogManager.showConfirmDialog(
            title, message, titleButtonPositive, titleButtonNegative, isPayment,
            listener
        )
    }

    override fun handleApiError(apiError: Throwable) {
        if (apiError is RetrofitException) {
            if (apiError.getErrorCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                apiError.getMsgError()?.let {
                    showAlertDialog(message = it,
                        listener = object : DialogAlert.Companion.OnButtonClickedListener {
                            override fun onPositiveClicked() {
                                // TODO reLogin
                            }
                        })
                }
                return
            }
            if (apiError.getErrorCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                apiError.getMsgError()?.let {
                    showAlertDialog(message = it)
                }
                return
            }
            apiError.getMsgError()?.let {
                showAlertDialog(message = it)
            }
        }
    }

    protected fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }
}
