package com.karthik.supportdesk.model

import com.karthik.supportdesk.R

data class OnBoardingItem(
    val image: Int,
    val title: String,
    val description: String
) {

    companion object {

        fun createItems(): List<OnBoardingItem> {
            val items = ArrayList<OnBoardingItem>()

            val firstItem = OnBoardingItem(R.drawable.target, "Stay focused on the day ahead", "Manage incoming tickets faster with quick swipe actions")
            val secondItem = OnBoardingItem(R.drawable.repair, "Improve your resolution times", "act upon multiple tickets in one go, using bulk actions")
            val thirdItem = OnBoardingItem(R.drawable.context, "Get complete context of the ticket", "View conversations in detail & update relevant information")
            val fourthItem = OnBoardingItem(R.drawable.notification, "Stay up-to-date", "Receive instant notifications for tickets & service tasks")

            items.add(firstItem)
            items.add(secondItem)
            items.add(thirdItem)
            items.add(fourthItem)

            return items
        }

    }

}