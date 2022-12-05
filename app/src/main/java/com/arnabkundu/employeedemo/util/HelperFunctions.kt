@file:Suppress("DEPRECATION")

package com.arnabkundu.employeedemo.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.widget.NestedScrollView
import com.arnabkundu.employeedemo.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*


object HelperFunctions {

    fun EditText.value(): String {
        return text.toString().trim()
    }

    fun EditText.onSearchSubmit(func: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                func()
            }
            true
        }
    }

    @SuppressLint("InflateParams")
    fun Context.showDialog(
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

    fun NestedScrollView.setAnimatedTitle(
        layoutTitleTextView: TextView,
        navbarTitleTextView: TextView,
        title: String
    ) {
        /*
         NOTE: please add android:windowSoftInputMode="stateHidden|adjustResize"
         to show the bottom layout on keyboard shown
         */
        val scrollBounds = Rect()
        getHitRect(scrollBounds)
        setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, _, _, _ ->
            if (layoutTitleTextView.getLocalVisibleRect(scrollBounds)) {
                if (!layoutTitleTextView.getLocalVisibleRect(scrollBounds)
                    || scrollBounds.height() < layoutTitleTextView.height
                ) {
                    navbarTitleTextView.text = ""
                } else {
                    navbarTitleTextView.text = ""
                }
            } else {
                @SuppressLint("SetTextI18n")
                navbarTitleTextView.text = title
            }
        })
    }

    fun EditText.showKeyboard() {
        (this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun EditText.hideKeyboard() {
        (this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.HIDE_IMPLICIT_ONLY)
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
