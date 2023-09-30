package com.example.recipe.data.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.recipe.R
import com.example.recipe.databinding.ItemPopularBinding
import com.example.recipe.models.recipes.ResponseRecipes.Result
import com.example.recipe.utils.Constants
import javax.inject.Inject

class PopularAdapter @Inject constructor() : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private lateinit var binding: ItemPopularBinding
    private var items = emptyList<Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int) = position
    override fun getItemId(position: Int) = position.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.apply {
//                texts
                popularNameText.text = item.title
                popularPriceTxt.text = "${item.pricePerServing} $"
//                Image
                val splitImage = item.image!!.split(".")
                var imageSize =
                    splitImage[1].replace(Constants.OLD_IMAGE_SIZE, Constants.NEW_IMAGE_SIZE)
//                popularImage.load("${splitImage[0]}-$imageSize") {
                popularImage.load(item.image!!) {
                    crossfade(true)
                    crossfade(800)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    error(R.drawable.ic_placeholder)
                }
                //            click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!)
                    }
                }
            }
        }
    }

    //    define DiffUtils
    fun setData(data: List<Result>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)

    }

    //    setClickListener
    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

}