package com.example.recipe.ui.register

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.recipe.R
import com.example.recipe.databinding.FragmentRegisterBinding
import com.example.recipe.models.register.BodyRegister
import com.example.recipe.utils.Constant
import com.example.recipe.utils.NetworkChecker
import com.example.recipe.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //    binding
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    //    View Model
    private val viewModel: RegisterViewModel by viewModels()

    //    Injects
    @Inject
    lateinit var body: BodyRegister

    @Inject
    lateinit var networkChecker: NetworkChecker
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
                if (!it.toString().isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(it.toString())
                        .matches()
                ) emailLayout.error = ""
                else emailLayout.error = getString(R.string.emailNotValid)
            }

            submitBtn.setOnClickListener {
                var email = emailEdt.text.toString()
                var firstName = nameEdt.text.toString()
                var lastName = lastNameEdt.text.toString()
                var userName = userNameEdt.text.toString()
                body.email = email
                body.firstName = firstName
                body.lastName = lastName
                body.username = userName
//                Check network
                lifecycleScope.launchWhenStarted {
                    networkChecker.checkNetworkAvailability().collect{state->

                    }
                }
                viewModel.callRegisterApi(Constant.MY_API_KEY, body)

            }

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}