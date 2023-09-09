package com.example.recipe.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.recipe.BuildConfig
import com.example.recipe.R
import com.example.recipe.databinding.FragmentSplashBinding

/**Created by Arezou-Ghorbani on 09,September,2023,ArezouGhorbaniii@gmail.com**/
class SplashFragment : Fragment() {
    //    binding
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        init views
        binding.apply {
//            dynamic bg
            bgImg.load(R.drawable.bg_splash)
//            app version
            versionTxt.text = "${getString(R.string.version)} : ${BuildConfig.VERSION_NAME}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}