package com.karthik.supportdesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SupportDesk)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }
}