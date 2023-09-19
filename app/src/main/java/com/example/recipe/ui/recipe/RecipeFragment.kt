package com.example.recipe.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.recipe.databinding.FragmentRecipeBinding
import com.example.recipe.models.recipes.ResponseRecipes
import com.example.recipe.ui.adapters.PopularAdapter
import com.example.recipe.ui.adapters.RecentAdapter
import com.example.recipe.utils.Constant
import com.example.recipe.utils.NetworkRequest
import com.example.recipe.viewmodel.RecipeViewModel
import com.example.recipe.viewmodel.RegisterViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
    @Inject
    lateinit var recentAdapter: RecentAdapter

    //    others
    private var autoIndex = 0
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
        viewModel.callRecentApi()
        loadPopularData()
        loadRecentData()
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
                                automaticScroll(data.results)

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

    private fun loadRecentData() {
        binding.apply {
            viewModel.recentRecipesLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        showHideShimmer(true, recipesList)
                    }
                    is NetworkRequest.Success -> {
                        showHideShimmer(false, recipesList)

                        response.data?.let { data ->
                            if (data.results!!.isNotEmpty()) {
                                recentAdapter.setData(data.results)
                                initRecentRecycler()

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
//        scroll just one with snapHelper
        val snapHelper = LinearSnapHelper()
        binding.popularList.setUpRecyclerView(
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            ),
            popularAdapter
        )
        snapHelper.attachToRecyclerView(binding.popularList)
        popularAdapter.setOnItemClickListener {
//go to detailPagr
        }
    }
    private fun initRecentRecycler() {

        binding.recipesList.setUpRecyclerView(
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            ),
            recentAdapter
        )
//        popularAdapter.setOnItemClickListener {
//go to detailPage
    }

    //automatic scroll
    private fun automaticScroll(items: List<ResponseRecipes.Result>) {
        lifecycleScope.launchWhenStarted {
            repeat(100) {
                delay(Constant.REPEAT_TIME)
                if (autoIndex < items.size) {
                    autoIndex += 1
                } else autoIndex = 0
                binding.popularList.smoothScrollToPosition(autoIndex)
            }
        }
    }
}