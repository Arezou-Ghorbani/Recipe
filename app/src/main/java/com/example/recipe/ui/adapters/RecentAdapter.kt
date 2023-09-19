package com.example.recipe.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.recipe.R
import com.example.recipe.databinding.ItemPopularBinding
import com.example.recipe.databinding.ItemRecentRecipesBinding
import com.example.recipe.models.recipes.ResponseRecipes
import setDynamicColorOnTextView
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 18,September,2023,Arezoughorbaniii@gmail.com**/
class RecentAdapter @Inject constructor() : RecyclerView.Adapter<RecentAdapter.ViewHolder>() {
    private lateinit var binding: ItemRecentRecipesBinding
    private var items = emptyList<ResponseRecipes.Result>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentAdapter.ViewHolder {
        binding =
            ItemRecentRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
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
                recipeDescTxt.text = item.summary
                recipeLikeTxt.text = item.aggregateLikes.toString()
                recipeHealthTxt.text = item.healthScore.toString()
                recipeTimeTxt.text = item.readyInMinutes.toString() + "min"
                if (item.vegan!!)
                    recipeVeganTxt.setDynamicColorOnTextView(
                        R.color.caribbean_green
                    )
                else recipeVeganTxt.setDynamicColorOnTextView(
                    R.color.gray
                )
//                healthy
                when (item.healthScore) {
                    in 90..100 -> recipeHealthTxt.setDynamicColorOnTextView(R.color.caribbean_green)
                    in 60..89 -> recipeHealthTxt.setDynamicColorOnTextView(R.color.chineseYellow)
                    in 0..59 -> recipeHealthTxt.setDynamicColorOnTextView(R.color.tart_orange)
                    else -> recipeHealthTxt.setDynamicColorOnTextView(R.color.gray)
                }

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