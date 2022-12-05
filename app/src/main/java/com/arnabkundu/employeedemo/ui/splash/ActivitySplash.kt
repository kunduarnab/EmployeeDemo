package com.arnabkundu.employeedemo.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arnabkundu.employeedemo.R
import com.arnabkundu.employeedemo.ui.dashboard.ActivityDashboard
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class ActivitySplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_splash)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener {
            // Can execute any job here, like fetching some initial data or sync database
            runBlocking { delay(1000) }
            return@addOnPreDrawListener true
        }

        Intent(this, ActivityDashboard::class.java).also {
            startActivity(it)
            finish()
        }
    }
}