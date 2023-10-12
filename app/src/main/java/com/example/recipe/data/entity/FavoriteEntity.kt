package com.example.recipe.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipe.models.detail.ResponseDetail
import com.example.recipe.utils.Constants
/**Created by Arezou-Ghorbani on 9,October,2023,Arezoughorbaniii@gmail.com**/
@Entity(tableName = Constants.FAVORITE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val result: ResponseDetail
)
