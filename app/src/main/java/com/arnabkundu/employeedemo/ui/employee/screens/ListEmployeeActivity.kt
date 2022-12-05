package com.arnabkundu.employeedemo.ui.employee.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arnabkundu.employeedemo.databinding.ActivityListEmployeeBinding

class ListEmployeeActivity : AppCompatActivity() {
    private lateinit var ui: ActivityListEmployeeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityListEmployeeBinding.inflate(layoutInflater)
        setContentView(ui.root)
    }
}