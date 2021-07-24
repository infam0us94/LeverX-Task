package com.project.leverxtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.leverxtask.R
import com.project.leverxtask.repository.model.News

class DataAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var listNews = ArrayList<News>()

    fun setNews(list: List<News>) {
        this.listNews = list as ArrayList<News>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNews[position])
    }

    override fun getItemCount(): Int = listNews.size
}