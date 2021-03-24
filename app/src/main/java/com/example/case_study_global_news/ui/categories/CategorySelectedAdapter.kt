package com.example.case_study_global_news.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.case_study_global_news.data.network.models.CategoryInfo
import com.example.case_study_global_news.databinding.CategoryCardItemsBinding

class CategorySelectedAdapter(private val listener: OnCategorySelectedClickListener) :
    RecyclerView.Adapter<CategorySelectedViewHolder>() {
    var categoryInfoList: List<CategoryInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySelectedViewHolder {
        return CategorySelectedViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategorySelectedViewHolder, position: Int) {
        holder.bind(categoryInfoList[position], position, listener)
    }

    override fun getItemCount(): Int = categoryInfoList.size
}

class CategorySelectedViewHolder private constructor(private val binding: CategoryCardItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        // A function that is used to create a new instance of ViewHolder class
        fun from(parent: ViewGroup): CategorySelectedViewHolder {
            val binding = CategoryCardItemsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return CategorySelectedViewHolder(binding)
        }
    }

    // populate category selected card items here
    fun bind(value: CategoryInfo, positon: Int, listener: OnCategorySelectedClickListener) {
        binding.categorySelectedCardTitle.text = value.name
        binding.categorySelectedCardDescription.text = value.description

        binding.categoryNewsCard.setOnClickListener {
            listener.onCategorySelectedClick(value)
        }
    }
}

// helper class to handle a click event
class OnCategorySelectedClickListener(private val listener: (info: CategoryInfo) -> Unit) {
    fun onCategorySelectedClick(info: CategoryInfo) = listener.invoke(info)
}