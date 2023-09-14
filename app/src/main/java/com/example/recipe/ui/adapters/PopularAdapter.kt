package com.example.recipe.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ItemPopularBinding
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.models.recipes.ResponseRecipes.Result
import javax.inject.Inject

class PopularAdapter @Inject constructor() : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private lateinit var binding: ItemPopularBinding
    private var items = emptyList<Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.apply {
//                texts
                popularNameText.text = item.title
                popularPriceTxt.text = "${item.pricePerServing} $"
            }
        }
    }

    //    define DiffUtils

}