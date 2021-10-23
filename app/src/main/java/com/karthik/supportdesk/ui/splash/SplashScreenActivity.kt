package com.karthik.supportdesk.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.karthik.supportdesk.ui.login.LoginActivity
import com.karthik.supportdesk.ui.onboarding.OnBoardingActivity
import com.karthik.supportdesk.utility.Constants

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent: Intent
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        if (preference.getBoolean(Constants.PREFERENCE_ONBOARD, false)) {
            intent = Intent(this, LoginActivity::class.java)
        } else {
            intent = Intent(this, OnBoardingActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}