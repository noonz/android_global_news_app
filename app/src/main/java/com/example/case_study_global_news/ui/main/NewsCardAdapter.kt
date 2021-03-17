package com.example.case_study_global_news.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.network.models.Articles
import kotlin.random.Random

class NewsCardAdapter():RecyclerView.Adapter<NewsViewHolder>() {
    var data: List<Articles> = emptyList()
    override fun onViewRecycled(holder: NewsViewHolder) {
        super.onViewRecycled(holder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size
}
class NewsViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun from(parent: ViewGroup): NewsViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_card_item, parent, false)

            return NewsViewHolder(itemView)
        }
    }

    val titleView: TextView = itemView.findViewById(R.id.news_card_title)

    fun bind(value: Articles, positon: Int) {
        titleView.text = value.title

    }
}