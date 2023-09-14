package com.example.recipe.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.databinding.FragmentRecipeBinding
import com.example.recipe.ui.adapters.PopularAdapter
import com.example.recipe.utils.NetworkRequest
import com.example.recipe.viewmodel.RecipeViewModel
import com.example.recipe.viewmodel.RegisterViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import setUpRecyclerView
import showSnackBar
import javax.inject.Inject

/**Created by Arezou-Ghorbani on 10,September,2023,ArezouGhorbaniii@gmail.com**/
@AndroidEntryPoint
class RecipeFragment : Fragment() {
    //    Binding
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    //    viewModels
    private val registerViewModel: RegisterViewModel by viewModels()
    private val viewModel: RecipeViewModel by viewModels()

    //    adapter
    @Inject
    lateinit var popularAdapter: PopularAdapter
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
//        call popular recipeApi
        viewModel.callPopularApi()
        loadPopularData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun showUserName() {
        registerViewModel.readData.collect {
            var userName = it.userName
            binding.usernameTxt.text = "Hello, $userName ${getEmojiByUnicode()}"
        }
    }

    private fun getEmojiByUnicode(): String {
        return String(Character.toChars(0x1f44b))
    }

    private fun loadPopularData() {
        binding.apply {
            viewModel.popularliveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        showHideShimmer(true, popularList)

                    }
                    is NetworkRequest.Success -> {
                        showHideShimmer(false, popularList)

                        response.data?.let { data ->
                            if (data.results!!.isNotEmpty()) {
                                popularAdapter.setData(data.results)
                                initPopularRecycler()
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        showHideShimmer(false, popularList)
                        binding.root.showSnackBar(response.message!!)
                    }
                }
            }

        }
    }

    private fun showHideShimmer(isVisible: Boolean, shimmer: ShimmerRecyclerView) {
        shimmer.apply {
            if (isVisible) shimmer.showShimmer() else shimmer.hideShimmer()
        }
    }

    private fun initPopularRecycler() {
        binding.popularList.setUpRecyclerView(
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            ),
            popularAdapter
        )
        popularAdapter.setOnItemClickListener {
//go to detailPagr
        }
    }


}