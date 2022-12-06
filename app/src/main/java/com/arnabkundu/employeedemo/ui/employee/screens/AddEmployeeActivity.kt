package com.arnabkundu.employeedemo.ui.employee.screens

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arnabkundu.employeedemo.data.db.entities.Employee
import com.arnabkundu.employeedemo.databinding.ActivityAddEmployeeBinding
import com.arnabkundu.employeedemo.ui.employee.viewmodel.EmployeeViewModel
import com.arnabkundu.employeedemo.util.DialogListener
import com.arnabkundu.employeedemo.util.HelperFunctions.hide
import com.arnabkundu.employeedemo.util.HelperFunctions.show
import com.arnabkundu.employeedemo.util.HelperFunctions.showCustomDialog
import com.arnabkundu.employeedemo.util.HelperFunctions.snack
import com.arnabkundu.employeedemo.util.HelperFunctions.value
import com.arnabkundu.employeedemo.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEmployeeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var ui: ActivityAddEmployeeBinding
    private val vm: EmployeeViewModel by viewModels()
    private var savedEmployee: Employee? = null
    private var ht = "Add"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(ui.root)

        try {
            savedEmployee = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getParcelable("savedEmployee", Employee::class.java)
            } else {
                intent.extras?.getParcelable("savedEmployee")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        savedEmployee?.let {
            with(ui) {
                ht = "Update"
                name.setText(it.employee_name)
                number.setText(it.employee_number)
                salary.setText(it.employee_salary)
            }
        } ?: kotlin.run {
            ht = "Add"
        }


        ui.a1.text = "$ht Employee"
        ui.a2.text = "$ht Employee"
        ui.nextBtn.text = ht


        with(ui) {
            backBtn.setOnClickListener(this@AddEmployeeActivity)
            nextBtn.setOnClickListener(this@AddEmployeeActivity)
        }

        vm.responseState.observe(this) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Error -> {
                        val error = resource.message
                        snack(ui.root, error.toString())
                        ui.progressBar.hide()
                        ui.nextBtn.show()
                    }
                    is Resource.Loading -> {
                        ui.progressBar.show()
                        ui.nextBtn.hide()
                    }
                    is Resource.Success -> {
                        ui.progressBar.hide()
                        ui.nextBtn.show()
                        snack(ui.root, "Employee added successfully")
                        with(ui) {
                            name.setText("")
                            number.setText("")
                            salary.setText("")
                        }
                    }
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        if (p0 == ui.backBtn) {
            onBackPressedDispatcher.onBackPressed()
        }
        if (p0 == ui.nextBtn) {
            if (savedEmployee != null) {
                showCustomDialog(
                    "Update: employee details",
                    "Are you sure you want to update this employee details?",
                    isNoVisible = true,
                    yesText = "UPDATE",
                    noText = "CANCEL",
                    isCancellable = true,
                    object : DialogListener {
                        override fun onPressedYes(dialog: BottomSheetDialog) {
                            dialog.dismiss()
                            val data = Employee(
                                savedEmployee!!.uid,
                                ui.name.value(),
                                ui.number.value(),
                                ui.salary.value()
                            )
                            vm.updateEmployee(data)
                            onBackPressedDispatcher.onBackPressed()
                        }

                        override fun onPressedNo(dialog: BottomSheetDialog) {
                            dialog.dismiss()
                        }
                    }
                )
            } else {
                vm.insertEmployee(
                    Employee(
                        0,
                        ui.name.value(),
                        ui.number.value(),
                        ui.salary.value()
                    )
                )
            }
        }
    }
}