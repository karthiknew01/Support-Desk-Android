package com.karthik.supportdesk.ui.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.karthik.supportdesk.R
import com.karthik.supportdesk.model.OnBoardingItem
import com.karthik.supportdesk.ui.login.LoginActivity
import com.karthik.supportdesk.utility.Constants.PREFERENCE_NAME
import com.karthik.supportdesk.utility.Constants.PREFERENCE_ONBOARD
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : AppCompatActivity() {

    var onBoardingAdapter:OnBoardingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setUIActions()
        setOnBoardingItems()
        setOnBoardingIndicators()
        setCurrentOnBoardingIndicator(0)

        val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
        editor.putBoolean(PREFERENCE_ONBOARD, true)
        editor.apply()
    }

    private fun setUIActions() {
        buttonOnBoarding.setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingAdapter?.itemCount ?: 0) {
                onBoardingViewPager.currentItem = onBoardingViewPager.currentItem + 1
            } else {
                moveToNextScreen()
            }
        }

        onBoardingViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnBoardingIndicator(position)
            }
        })

    }

    private fun setOnBoardingItems() {
        val items = OnBoardingItem.createItems()
        onBoardingAdapter = OnBoardingAdapter(items)
        onBoardingViewPager.adapter = onBoardingAdapter
    }

    private fun setOnBoardingIndicators() {
        val itemCount = onBoardingAdapter?.itemCount ?: 0
        val indicators = arrayOfNulls<ImageView>(itemCount)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        for (i in 0 until itemCount) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_indicator_inactive))
            indicators[i]?.layoutParams = layoutParams
            layoutOnboarding.addView(indicators[i])
        }
    }

    private fun setCurrentOnBoardingIndicator(position: Int) {
        val childCount = layoutOnboarding.childCount

        for (i in 0 until childCount) {
            val imageView = layoutOnboarding.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_indicator_active))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.onboarding_indicator_inactive))
            }

            if (position == (onBoardingAdapter?.itemCount ?: 0) - 1) {
                buttonOnBoarding.text = getString(R.string.get_started)
            } else {
                buttonOnBoarding.text = getString(R.string.next)
            }
        }
    }

    private fun moveToNextScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}