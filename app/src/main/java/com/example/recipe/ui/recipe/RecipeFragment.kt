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
import com.example.recipe.utils.Constants
import com.example.recipe.utils.NetworkRequest
import com.example.recipe.viewmodel.RecipeViewModel
import com.example.recipe.viewmodel.RegisterViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import onceObserve
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
    private var autoScrollIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            lifecycleScope.launchWhenStarted {
            showUserName()
        }
//        callData-cash
        callPopularData()
        callRecentData()
        loadPopularData()
        loadRecentData()
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
                                fillAdapterData(data.results.toMutableList())

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

    private fun callRecentData() {
        initRecentRecycler()
        viewModel.readPopularFromDb.onceObserve(viewLifecycleOwner) { database ->
            if (database.isNotEmpty() && database.size > 1) {
                database[1].responseRecipes.results?.let { result ->
                    setupLoading(false, binding.recipesList)
                    recentAdapter.setData(result)
                }
            } else viewModel.callRecentApi()
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
    private fun autoScrollPopular(list: List<ResponseRecipes.Result>) {
        lifecycleScope.launchWhenCreated {
            repeat(Constants.REPEAT_TIME) {
                delay(Constants.DELAY_TIME)
                if (autoScrollIndex < list.size) {
                    autoScrollIndex += 1
                } else {
                    autoScrollIndex = 0
                }
                binding.popularList.smoothScrollToPosition(autoScrollIndex)
            }
        }
    }

    //---Popular---//
    private fun callPopularData() {
        initPopularRecycler()
        viewModel.readPopularFromDb.onceObserve(viewLifecycleOwner) { database ->
            if (database.isNotEmpty()) {
                database[0].responseRecipes.results?.let { result ->
                    setupLoading(false, binding.popularList)
                    fillAdapterData(result.toMutableList())
                }
            } else {
                viewModel.callPopularApi()
            }
        }
    }

    private fun fillAdapterData(data: MutableList<ResponseRecipes.Result>) {
        popularAdapter.setData(data)
        autoScrollPopular(data)

    }

    private fun setupLoading(isShownLoading: Boolean, shimmer: ShimmerRecyclerView) {
        shimmer.apply {
            if (isShownLoading) showShimmer() else hideShimmer()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}