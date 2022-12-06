package com.arnabkundu.employeedemo.ui.employee.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnabkundu.employeedemo.data.db.entities.Employee
import com.arnabkundu.employeedemo.databinding.ActivityListEmployeeBinding
import com.arnabkundu.employeedemo.ui.employee.adapter.AdapterEmployee
import com.arnabkundu.employeedemo.ui.employee.viewmodel.EmployeeViewModel
import com.arnabkundu.employeedemo.util.DialogListener
import com.arnabkundu.employeedemo.util.HelperFunctions.afterTextChanged
import com.arnabkundu.employeedemo.util.HelperFunctions.hide
import com.arnabkundu.employeedemo.util.HelperFunctions.show
import com.arnabkundu.employeedemo.util.HelperFunctions.showCustomDialog
import com.arnabkundu.employeedemo.util.ListListener
import com.arnabkundu.employeedemo.util.RecyclerViewMargin
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListEmployeeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var ui: ActivityListEmployeeBinding
    private lateinit var adapterEmployee: AdapterEmployee
    private val vm: EmployeeViewModel by viewModels()
    val tempList = ArrayList<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityListEmployeeBinding.inflate(layoutInflater)
        setContentView(ui.root)

        with(ui) {
            backBtn.setOnClickListener(this@ListEmployeeActivity)
            addNew.setOnClickListener(this@ListEmployeeActivity)
        }

        ui.keyword.afterTextChanged {
            onSearch(it)
        }

        ui.recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@ListEmployeeActivity)
            itemAnimator = DefaultItemAnimator()
            val decoration = RecyclerViewMargin(0, 1)
            addItemDecoration(decoration)
            adapterEmployee = AdapterEmployee(object : ListListener {
                override fun onDelete(employee: Employee) {
                    showCustomDialog(
                        "Delete: ${employee.employee_name}",
                        "Are you sure you want to delete this employee?",
                        isNoVisible = true,
                        yesText = "DELETE",
                        noText = "CANCEL",
                        isCancellable = true,
                        object : DialogListener {
                            override fun onPressedYes(dialog: BottomSheetDialog) {
                                dialog.dismiss()
                                vm.deleteEmployee(employee.uid)
                            }

                            override fun onPressedNo(dialog: BottomSheetDialog) {
                                dialog.dismiss()
                            }
                        }
                    )
                }

                override fun onEdit(employee: Employee) {
                    Intent(this@ListEmployeeActivity, AddEmployeeActivity::class.java).apply {
                        putExtra("savedEmployee", employee)
                        startActivity(this)
                    }
                }
            })
            adapter = adapterEmployee
        }

        vm.getAllEmployees().observe(this) {
            it?.let { list ->
                if (list.isEmpty()) {
                    ui.noData.show()
                    ui.recyclerView.hide()
                } else {
                    ui.noData.hide()
                    ui.recyclerView.show()
                }
                adapterEmployee.setData(list)
                tempList.clear()
                tempList.addAll(list)
            }
        }
    }

    private fun onSearch(keyword: String) {
        adapterEmployee.setData(tempList.filterIndexed { index, employee ->
            employee.employee_name.lowercase().contains(keyword.lowercase())
        })
    }

    override fun onClick(p0: View?) {
        if (p0 == ui.backBtn) {
            onBackPressedDispatcher.onBackPressed()
        }
        if (p0 == ui.addNew) {
            Intent(this, AddEmployeeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}