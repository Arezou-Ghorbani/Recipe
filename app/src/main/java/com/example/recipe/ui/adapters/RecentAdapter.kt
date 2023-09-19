package com.example.recipe.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.recipe.R
import com.example.recipe.databinding.ItemPopularBinding
import com.example.recipe.databinding.ItemRecentRecipesBinding
import com.example.recipe.models.recipes.ResponseRecipes
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 18,September,2023,Arezoughorbaniii@gmail.com**/
class RecentAdapter @Inject constructor() : RecyclerView.Adapter<RecentAdapter.ViewHolder>() {
    private lateinit var binding: ItemRecentRecipesBinding
    private var items = emptyList<ResponseRecipes.Result>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentAdapter.ViewHolder {
        binding =
            ItemRecentRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: RecentAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int) = position
    override fun getItemId(position: Int) = position.toLong()


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseRecipes.Result) {
            binding.apply {
//                text
                recipeNameTxt.text = item.title
                recipeDescTxt.text=item.summary
//                rv image
                recipeImg.load(item.image!!) {
                    crossfade(true)
                    crossfade(800)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    error(R.drawable.ic_placeholder)
                }
            }
        }
    }

    fun setData(data: List<ResponseRecipes.Result>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)

    }
}