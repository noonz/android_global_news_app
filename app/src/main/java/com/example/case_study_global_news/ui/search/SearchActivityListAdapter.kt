package com.example.case_study_global_news.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.case_study_global_news.data.network.models.WebArticleInfo
import com.example.case_study_global_news.databinding.NewsCardItemBinding

class SearchActivityListAdapter(private val listener: OnResultClickListener) :
    RecyclerView.Adapter<SearchResultsViewHolder>() {
    var searchResultInfoList: List<WebArticleInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        return SearchResultsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        holder.bind(searchResultInfoList[position], position, listener)
    }

    override fun getItemCount(): Int = searchResultInfoList.size
}

class SearchResultsViewHolder private constructor(private val binding: NewsCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        // A function that is used to create a new instance of ViewHolder class
        fun from(parent: ViewGroup): SearchResultsViewHolder {
            val binding = NewsCardItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return SearchResultsViewHolder(binding)
        }
    }

    // populate search result card items here
    fun bind(value: WebArticleInfo, positon: Int, listener: OnResultClickListener) {
        binding.newsCardTitle.text = value.title
        binding.newsCardDate.text = value.datePublished
        binding.newsCardPublisher.text = value.source.publisher
        Glide.with(itemView.context)
            .load(value.imageURL)
            .centerInside()
            .into(binding.newsCardImage)
        binding.newsCardImage.setOnClickListener {
            listener.onResultClick(value)
        }
    }
}

// helper class to handle a click event
class OnResultClickListener(private val listener: (info: WebArticleInfo) -> Unit) {
    fun onResultClick(info: WebArticleInfo) = listener.invoke(info)
}