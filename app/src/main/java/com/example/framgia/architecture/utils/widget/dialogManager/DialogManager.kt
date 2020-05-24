package mi.omiseno.smartphoneorder.utils.widget.dialogManager

import com.example.framgia.architecture.utils.widget.dialogManager.DialogAlert
import com.example.framgia.architecture.utils.widget.dialogManager.DialogConfirm

interface DialogManager {

    fun showLoading()

    fun showProcessing()

    fun hideLoading()

    fun onRelease()

    fun showAlertDialog(
        title: String,
        message: String,
        titleButton: String,
        listener: DialogAlert.Companion.OnButtonClickedListener?
    )

    fun showAlertDialog(
        title: String,
        message: String,
        titleButton: String,
        buttonBgColor: Int,
        buttonColor: Int,
        listener: DialogAlert.Companion.OnButtonClickedListener?
    )

    fun showConfirmDialog(
        title: String?,
        message: String?,
        titleButtonPositive: String,
        titleButtonNegative: String,
        isPayment: Boolean?,
        listener: DialogConfirm.OnButtonClickedListener?
    )
}
