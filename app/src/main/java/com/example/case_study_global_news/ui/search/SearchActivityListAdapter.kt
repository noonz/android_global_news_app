package com.example.case_study_global_news.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.case_study_global_news.data.network.models.Articles
import com.example.case_study_global_news.databinding.NewsCardItemBinding

class SearchActivityListAdapter(private val listener: OnResultClickListener) :
    RecyclerView.Adapter<SearchResultsViewHolder>() {
    var data: List<Articles> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        return SearchResultsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        holder.bind(data[position], position, listener)
    }

    override fun getItemCount(): Int = data.size
}

class SearchResultsViewHolder private constructor(private val binding: NewsCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): SearchResultsViewHolder {
            val binding = NewsCardItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return SearchResultsViewHolder(binding)
        }
    }

    fun bind(value: Articles, positon: Int, listener: OnResultClickListener) {
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
class OnResultClickListener(private val listener: (info: Articles) -> Unit) {
    fun onNewsClick(info: Articles) = listener.invoke(info)
}