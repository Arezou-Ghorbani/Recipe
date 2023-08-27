package com.example.recipe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipe.R
import com.example.recipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //    binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}