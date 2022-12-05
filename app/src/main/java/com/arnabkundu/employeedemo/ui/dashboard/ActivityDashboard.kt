package com.arnabkundu.employeedemo.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arnabkundu.employeedemo.databinding.ActivityDashboardBinding
import com.arnabkundu.employeedemo.ui.employee.screens.AddEmployeeActivity
import com.arnabkundu.employeedemo.ui.employee.screens.ListEmployeeActivity
import com.arnabkundu.employeedemo.util.DialogListener
import com.arnabkundu.employeedemo.util.HelperFunctions.showCustomDialog
import com.google.android.material.bottomsheet.BottomSheetDialog

class ActivityDashboard : AppCompatActivity(), View.OnClickListener {
    private lateinit var ui: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(ui.root)

        with(ui) {
            powerBtn.setOnClickListener(this@ActivityDashboard)
            addEmployeeBtn.setOnClickListener(this@ActivityDashboard)
            listEmployeeBtn.setOnClickListener(this@ActivityDashboard)
        }
    }

    override fun onClick(p0: View?) {
        if (p0 == ui.powerBtn) {
            showCustomDialog(
                "Exit App",
                "Are you sure you want to exit from this app?",
                isNoVisible = true,
                yesText = "EXIT",
                noText = "CANCEL",
                isCancellable = true,
                object : DialogListener {
                    override fun onPressedYes(dialog: BottomSheetDialog) {
                        dialog.dismiss()
                        finish()
                    }

                    override fun onPressedNo(dialog: BottomSheetDialog) {
                        dialog.dismiss()
                    }
                }
            )
        }
        if (p0 == ui.addEmployeeBtn) {
            Intent(this, AddEmployeeActivity::class.java).also {
                startActivity(it)
            }
        }
        if (p0 == ui.listEmployeeBtn) {
            Intent(this, ListEmployeeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}