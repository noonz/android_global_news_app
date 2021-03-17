package com.example.case_study_global_news.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.case_study_global_news.R
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class NewsCardAdapter:RecyclerView.Adapter<NewsViewHolder>() {
    var data: List<String> = emptyList()
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
            val titleView: TextView = itemView.findViewById(R.id.news_card_title)
            titleView.text = Random.nextInt(1000).toString()
            return NewsViewHolder(itemView)
        }
    }


    fun bind(value: String, positon: Int) {
    }
}