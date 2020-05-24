package com.example.framgia.architecture.utils.widget.dialogManager

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.framgia.architecture.R
import com.example.framgia.architecture.utils.rxbinding.clicks
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_alert.*

class DialogAlert : DialogFragment() {
    private lateinit var compositeDisposable: CompositeDisposable
    var listener: OnButtonClickedListener? = null
    private var title: String? = ""
    private var message: String? = ""
    private var titleBtn: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        compositeDisposable = CompositeDisposable()

        arguments?.let {
            title = it.getString(
                TITLE_EXTRA
            )
            message = it.getString(
                MESSAGE_EXTRA
            )
            titleBtn = it.getString(
                TITLE_BUTTON_EXTRA
            )
        }

        return inflater.inflate(R.layout.dialog_alert, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setCancelable(false)

        tvTitle.text = title
        tvContent.text = message
        if (!titleBtn.isNullOrEmpty()) {
            btnPositive.text = titleBtn
        }
        if (!title.isNullOrEmpty()) {
            tvTitle.visibility = View.GONE
        }

        compositeDisposable.add(
            btnPositive.clicks().subscribe {
                dismiss()
                listener?.onPositiveClicked()
            }
        )
    }

    override fun onDestroy() {
        this.compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        private const val TITLE_EXTRA = "TITLE_EXTRA"
        private const val MESSAGE_EXTRA = "MESSAGE_EXTRA"
        private const val TITLE_BUTTON_EXTRA = "TITLE_BUTTON_EXTRA"

        fun newInstance(
            title: String,
            message: String,
            titleBtn: String,
            listener: OnButtonClickedListener?
        ): DialogAlert {
            return DialogAlert().apply {
                arguments = Bundle().apply {
                    putString(
                        TITLE_EXTRA, title
                    )
                    putString(
                        MESSAGE_EXTRA, message
                    )
                    putString(
                        TITLE_BUTTON_EXTRA, titleBtn
                    )
                }
                this.listener = listener
            }
        }

        interface OnButtonClickedListener {
            fun onPositiveClicked()
        }
    }
}
