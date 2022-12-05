@file:Suppress("DEPRECATION")

package com.arnabkundu.employeedemo.util

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.arnabkundu.employeedemo.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*


object HelperFunctions {

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }

    fun EditText.value(): String {
        return text.toString().trim()
    }

    @SuppressLint("InflateParams")
    fun Context.showCustomDialog(
        title: String,
        message: String,
        isNoVisible: Boolean = false,
        yesText: String = "YES",
        noText: String = "NO",
        isCancellable: Boolean = true,
        listener: DialogListener,
    ) {
        val dialog = BottomSheetDialog(this)
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.layout_dialog, null)
        val titleTxt = view.findViewById<TextView>(R.id.title)
        val messageTxt = view.findViewById<TextView>(R.id.message)
        val noBtn = view.findViewById<TextView>(R.id.noBtn)
        val yesBtn = view.findViewById<TextView>(R.id.yesBtn)
        noBtn.visibility = if (isNoVisible) View.VISIBLE else View.GONE
        titleTxt.text = title
        messageTxt.text = message
        yesBtn.text = yesText
        noBtn.text = noText
        noBtn.setOnClickListener {
            listener.onPressedNo(dialog)
        }
        yesBtn.setOnClickListener {
            listener.onPressedYes(dialog)
        }
        dialog.setCancelable(isCancellable)
        dialog.setContentView(view)
        dialog.show()
    }

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.GONE
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun snack(rootView: View, message: String) {
        val snack = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
        snack.setAction("OK") {}
        snack.show()
    }
}
