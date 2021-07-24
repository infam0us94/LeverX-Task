package com.project.leverxtask.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.leverxtask.R
import com.project.leverxtask.list.ListNewsFragmentDirections
import com.project.leverxtask.repository.model.News

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.row_tv_title)
    private val image: ImageView = itemView.findViewById(R.id.row_img)
    private var link = ""

    init {
        itemView.setOnClickListener {
            itemView.findNavController()
                .navigate(ListNewsFragmentDirections.actionFirstFragmentToSecondFragment(link))
        }
    }

    fun bind(news: News) {
        title.text = news.title
        Glide.with(itemView.context)
            .load(news.image)
            .into(image)
        link = news.link
    }
}