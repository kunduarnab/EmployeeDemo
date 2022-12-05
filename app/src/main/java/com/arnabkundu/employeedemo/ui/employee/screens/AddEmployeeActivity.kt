package com.arnabkundu.employeedemo.ui.employee.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arnabkundu.employeedemo.databinding.ActivityAddEmployeeBinding

class AddEmployeeActivity : AppCompatActivity() {
    private lateinit var ui: ActivityAddEmployeeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(ui.root)
    }
}