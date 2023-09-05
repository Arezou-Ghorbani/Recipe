package com.example.recipe.di

import com.example.recipe.models.register.BodyRegister
import com.example.recipe.viewmodel.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

/**Created by Arezou-Ghorbani on 04,September,2023,ArezouGhorbaniii@gmail.com**/
@Module
@InstallIn(FragmentComponent::class)
object BodyModule {
    @Provides
    fun bodyRegister()=BodyRegister()
}