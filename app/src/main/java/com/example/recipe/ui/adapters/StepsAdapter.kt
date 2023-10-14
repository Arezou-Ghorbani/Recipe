package com.example.recipe.ui.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ItemStepBinding
import com.example.recipe.models.detail.ResponseDetail
import com.example.recipe.utils.BaseDiffUtils
import com.example.recipe.utils.Constants
import com.example.recipe.utils.minToHour
import javax.inject.Inject
/**Created by Arezou-Ghorbani on 10,October,2023,Arezoughorbaniii@gmail.com**/
class StepsAdapter @Inject constructor() : RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    private lateinit var binding: ItemStepBinding
    private var items = emptyList<ResponseDetail.AnalyzedInstruction.Step>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = Constants.STEPS_COUNT

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseDetail.AnalyzedInstruction.Step) {
            binding.apply {
                //Text
                stepTxt.text = "${adapterPosition + 1}"
                item.length?.let {
                    timeTxt.text = item.length.number!!.minToHour()
                }
                infoTxt.text = item.step
            }
        }
    }

    fun setData(data: List<ResponseDetail.AnalyzedInstruction.Step>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}