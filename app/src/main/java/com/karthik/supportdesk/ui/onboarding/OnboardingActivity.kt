package com.karthik.supportdesk.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.karthik.supportdesk.R
import com.karthik.supportdesk.model.OnBoardingItem
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    var onBoardingAdapter:OnBoardingAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SupportDesk)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setButtonActions()
        setOnBoardingItems()
        setOnboardingIndicators()
        setCurrentOnboardingIndicator(0)
    }

    private fun setButtonActions() {
        buttonOnBoarding.setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingAdapter?.itemCount ?: 0) {
                onBoardingViewPager.currentItem = onBoardingViewPager.currentItem + 1
            } else {
                Toast.makeText(applicationContext, "Get Started", Toast.LENGTH_LONG).show()
            }
        }

        onBoardingViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicator(position)
            }
        })

    }

    private fun setOnBoardingItems() {
        val items = ArrayList<OnBoardingItem>()

        val firstItem = OnBoardingItem(R.drawable.target, "Stay focused on the day ahead", "Manage incoming tickets faster with quick swipe actions")
        val secondItem = OnBoardingItem(R.drawable.repair, "Improve your resolution times", "act upon multiple tickets in one go, using bulk actions")
        val thirdItem = OnBoardingItem(R.drawable.context, "Get complete context of the ticket", "View conversations in detail & update relevant information")
        val fourthItem = OnBoardingItem(R.drawable.notification, "Stay up-to-date", "Receive instant notifications for tickets & service tasks")

        items.add(firstItem)
        items.add(secondItem)
        items.add(thirdItem)
        items.add(fourthItem)

        onBoardingAdapter = OnBoardingAdapter(items)
        onBoardingViewPager.adapter = onBoardingAdapter
    }

    private fun setOnboardingIndicators() {
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

    private fun setCurrentOnboardingIndicator(position: Int) {
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

}