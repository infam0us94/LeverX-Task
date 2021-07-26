package com.project.leverxtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.leverxtask.R
import com.project.leverxtask.repository.model.News

class DataAdapter(private var context: Context, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var listNews = ArrayList<News>()

    fun setNews(list: List<News>) {
        this.listNews = list as ArrayList<News>
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val title: TextView = itemView.findViewById(R.id.row_tv_title)
        private val image: ImageView = itemView.findViewById(R.id.row_img)
        private var link = ""

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(news: News) {
            title.text = news.title
            Glide.with(itemView.context)
                .load(news.image)
                .into(image)
            link = news.link
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(listNews[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNews[position])
    }

    override fun getItemCount(): Int = listNews.size

    interface OnItemClickListener {
        fun onItemClick(item: News)
    }
}