package com.example.recipe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.recipe.R
import com.example.recipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //    binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //    other
    private lateinit var naveHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setUp NaveHost
        naveHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNavigateUp(): Boolean {
        return naveHost.navController.navigateUp() || super.onNavigateUp()
    }
}