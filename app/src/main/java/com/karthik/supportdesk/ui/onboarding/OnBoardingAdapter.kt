package com.karthik.supportdesk.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karthik.supportdesk.R
import com.karthik.supportdesk.model.OnBoardingItem

class OnBoardingAdapter(private val items: List<OnBoardingItem>): RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageOnBoarding)
        val titleView: TextView = itemView.findViewById(R.id.textTitle)
        val descriptionView: TextView = itemView.findViewById(R.id.textDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_onboarding, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.imageView.setImageResource(item.image)
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description
    }

    override fun getItemCount(): Int = items.size

}
