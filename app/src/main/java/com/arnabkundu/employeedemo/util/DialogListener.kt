package com.arnabkundu.employeedemo.util

import com.google.android.material.bottomsheet.BottomSheetDialog

interface DialogListener {
    fun onPressedYes(dialog: BottomSheetDialog)
    fun onPressedNo(dialog: BottomSheetDialog)
}