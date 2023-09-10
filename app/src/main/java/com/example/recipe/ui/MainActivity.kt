package com.example.recipe.ui

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recipe.R
import com.example.recipe.databinding.ActivityMainBinding
import com.example.recipe.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class MainActivity : BaseActivity() {
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
//        set bottom nav Fragment replacement
        binding.mainBottomNave.setupWithNavController(naveHost.navController)
//        bottomNave background removed
        binding.mainBottomNave.background = null
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNavigateUp(): Boolean {
        return naveHost.navController.navigateUp() || super.onNavigateUp()
    }

}