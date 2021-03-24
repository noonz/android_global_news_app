package com.example.case_study_global_news.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.case_study_global_news.data.network.models.ArticleInfo
import com.example.case_study_global_news.databinding.NewsCardItemBinding

class MainCardAdapter(private val listener: OnArticleClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {
    var articleInfoList: List<ArticleInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(articleInfoList[position], position, listener)
    }

    override fun getItemCount(): Int = articleInfoList.size
}

class MainViewHolder private constructor(private val binding: NewsCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        // A function that is used to create a new instance of ViewHolder class
        fun from(parent: ViewGroup): MainViewHolder {
            val binding = NewsCardItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return MainViewHolder(binding)
        }
    }

    // populate home news card items here
    fun bind(value: ArticleInfo, positon: Int, listener: OnArticleClickListener) {
        binding.newsCardTitle.text = value.title
        binding.newsCardDate.text = value.datePublished
        binding.newsCardPublisher.text = value.source.publisher
        Glide.with(itemView.context)
            .load(value.imageURL)
            .centerInside()
            .into(binding.newsCardImage)
        binding.newsCardImage.setOnClickListener {
            listener.onArticleClick(value)
        }
    }
}

// helper class to handle a click event
class OnArticleClickListener(private val listener: (info: ArticleInfo) -> Unit) {
    fun onArticleClick(info: ArticleInfo) = listener.invoke(info)
}