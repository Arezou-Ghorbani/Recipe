package com.example.recipe.ui.adapters

import androidx.recyclerview.widget.DiffUtil

/**Created by Arezou-Ghorbani on 12,October,2023,Arezoughorbaniii@gmail.com**/
class BaseDiffUtils<T>(private val oldItems: List<T>, private val newItems: List<T>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] === newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] === newItems[newItemPosition]
    }
}