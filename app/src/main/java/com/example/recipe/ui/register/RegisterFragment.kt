package com.example.recipe.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import coil.load
import com.example.recipe.R
import com.example.recipe.databinding.FragmentRegisterBinding
import com.example.recipe.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //    binding
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    //    View Model
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViews
        binding?.apply {
            coverImage.load(R.drawable.register_logo)
//            email
            emailEdt.addTextChangedListener {
                if (it.toString().contains("@"))
                    emailLayout.error = getString(R.string.emailNotValid)
                else emailLayout.error = ""
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}