package com.arnabkundu.employeedemo.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arnabkundu.employeedemo.databinding.ActivityDashboardBinding

class ActivityDashboard : AppCompatActivity() {
    private lateinit var ui: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(ui.root)
    }
}