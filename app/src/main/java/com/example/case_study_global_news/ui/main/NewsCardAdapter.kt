package com.example.case_study_global_news.ui.main

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.case_study_global_news.R
import com.example.case_study_global_news.data.network.models.Articles
import com.example.case_study_global_news.databinding.NewsCardItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NewsCardAdapter(private val listener: OnNewsClickListener) :
    RecyclerView.Adapter<NewsViewHolder>() {
    var data: List<Articles> = emptyList()
    override fun onViewRecycled(holder: NewsViewHolder) {
        super.onViewRecycled(holder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position], position, listener)
    }

    override fun getItemCount(): Int = data.size
}

class NewsViewHolder private constructor(private val binding: NewsCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): NewsViewHolder {
            val binding = NewsCardItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return NewsViewHolder(binding)
//            val itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.news_card_item, parent, false)
//
//            return NewsViewHolder(itemView)
        }
    }

    fun bind(value: Articles, positon: Int, listener: OnNewsClickListener) {



        binding.newsCardTitle.text = value.title
        binding.newsCardDate.text = value.datePublished
        binding.newsCardPublisher.text = value.source.publisher
        Glide.with(itemView.context)
            .load(value.imageURL)
            .centerInside()
            .into(binding.newsCardImage)
        binding.newsCardImage.setOnClickListener {
            listener.onNewsClick(value)
        }

    }
}

class OnNewsClickListener(private val listener: (info: Articles) -> Unit) {
    fun onNewsClick(info: Articles) = listener.invoke(info)
}