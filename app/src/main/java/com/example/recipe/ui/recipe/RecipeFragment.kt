package com.example.recipe.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.recipe.databinding.FragmentRecipeBinding
import com.example.recipe.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

/**Created by Arezou-Ghorbani on 10,September,2023,ArezouGhorbaniii@gmail.com**/
@AndroidEntryPoint
class RecipeFragment : Fragment() {
    //    Binding
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    //    viewModels
    private val registerViewModel: RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
        lifecycleScope.launchWhenStarted {
            showUserName()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    suspend fun showUserName() {
        registerViewModel.readData.collect {
            var userName = it.userName
            binding.usernameTxt.text = "Hello, $userName ${getEmojiByUnicode()}"
        }
    }

    private fun getEmojiByUnicode(): String {
        return String(Character.toChars(0x1f44b))
    }
}