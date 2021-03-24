package com.example.case_study_global_news.ui.section

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
import com.example.case_study_global_news.data.network.models.Categories
import com.example.case_study_global_news.databinding.NewsCardItemBinding
import com.example.case_study_global_news.databinding.SectionCardItemsBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SectionNewsDetailsAdapter(private val listener: OnSectionsNewsClickListener) :
    RecyclerView.Adapter<SectionDetailsViewHolder>() {
    var data: List<Categories> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionDetailsViewHolder {
        return SectionDetailsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SectionDetailsViewHolder, position: Int) {
        holder.bind(data[position], position, listener)
    }

    override fun getItemCount(): Int = data.size
}

class SectionDetailsViewHolder private constructor(private val binding: SectionCardItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): SectionDetailsViewHolder {
            val binding = SectionCardItemsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return SectionDetailsViewHolder(binding)
        }
    }

    fun bind(value: Categories, positon: Int, listener: OnSectionsNewsClickListener) {

        binding.sectionNewsCardTitle.text = value.name
        binding.sectionNewsCardDescription.text = value.description

        binding.sectionNewsCard.setOnClickListener {
            listener.onNewsClick(value)
        }

    }
}

class OnSectionsNewsClickListener(private val listener: (info: Categories) -> Unit) {
    fun onNewsClick(info: Categories) = listener.invoke(info)
}