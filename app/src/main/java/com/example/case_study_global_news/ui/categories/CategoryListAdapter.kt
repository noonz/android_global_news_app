package com.example.case_study_global_news.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.case_study_global_news.R
import com.example.case_study_global_news.databinding.CategoryListItemBinding

class CategoryListAdapter(private val listener: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    var searchResultInfoList: List<String> =
        listOf("Business","Sports","Entertainment","Technology","Science","Health","General")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(searchResultInfoList[position], position, listener)
    }

    override fun getItemCount(): Int = searchResultInfoList.size
}

class CategoryViewHolder private constructor(private val binding: CategoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        // A function that is used to create a new instance of ViewHolder class
        fun from(parent: ViewGroup): CategoryViewHolder {
            val binding = CategoryListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding)
        }
    }

    // populate search result card items here
    fun bind(value: String, positon: Int, listener: OnCategoryClickListener) {
        binding.categoryName.text = value

        when(value){
            "Business" -> binding.categoryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.business,0,0,0)
            "Sports" -> binding.categoryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sports,0,0,0)
            "Entertainment" -> binding.categoryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.entertainment,0,0,0)
            "Technology" -> binding.categoryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.technology,0,0,0)
            "Science" -> binding.categoryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.science,0,0,0)
            "Health" -> binding.categoryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.health,0,0,0)
            "General" -> binding.categoryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.general,0,0,0)
        }

        binding.categoryName.setOnClickListener {
            listener.onResultClick(value.toLowerCase())
        }
    }
}

// helper class to handle a click event
class OnCategoryClickListener(private val listener: (info: String) -> Unit) {
    fun onResultClick(info: String) = listener.invoke(info)
}