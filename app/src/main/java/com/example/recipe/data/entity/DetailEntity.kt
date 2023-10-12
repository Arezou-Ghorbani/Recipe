package com.example.recipe.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipe.models.detail.ResponseDetail
import com.example.recipe.utils.Constants
/**Created by Arezou-Ghorbani on 12,October,2023,Arezoughorbaniii@gmail.com**/
@Entity(tableName = Constants.DETAIL_TABLE_NAME)
data class DetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val result: ResponseDetail
)
