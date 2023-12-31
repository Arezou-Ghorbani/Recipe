package com.example.recipe.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.databinding.FragmentMenuBinding
import com.example.recipe.utils.onceObserve
import com.example.recipe.viewmodel.MenuViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

/**Created by Arezou-Ghorbani on 23,September,2023,ArezouGhorbaniii@gmail.com**/
@AndroidEntryPoint
class MenuFragment : BottomSheetDialogFragment() {
    //Binding
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    //    viewModel  ->without kotlin delegate for example "context" is not available
    private lateinit var viewModel: MenuViewModel

    //    other
    private var chipCounter = 1
    private var chipMealTitle = ""
    private var chipMealId = 0
    private var chipDietTitle = ""
    private var chipDietId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            fillChips(viewModel.mealsList(), mealChipGroup)
            fillChips(viewModel.dietsList(), dietChipGroup)
            //Read from menu stored data
            viewModel.readMenuStoredItems.asLiveData().onceObserve(viewLifecycleOwner) {
                chipMealTitle = it.meal
                chipDietTitle = it.diet
                updateChip(it.mealId, mealChipGroup)
                updateChip(it.dietId, dietChipGroup)
            }
                //Meal chips - click
            mealChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                var chip: Chip
                checkedIds.forEach {
                    chip = group.findViewById(it)
                    chipMealTitle = chip.text.toString().lowercase()
                    chipMealId = it
                }
            }
            //Diet chips - click
            dietChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                var chip: Chip
                checkedIds.forEach {
                    chip = group.findViewById(it)
                    chipDietTitle = chip.text.toString().lowercase()
                    chipDietId = it
                }
            }
            //Submit
            submitBtn.setOnClickListener {
                viewModel.saveToStore(chipMealTitle, chipMealId, chipDietTitle, chipDietId)
                findNavController().navigate(
                    com.example.recipe.ui.menu.MenuFragmentDirections.actionMenuToRecipe().setIsUpdateData(true)

                )

            }
        }
    }

    private fun fillChips(list: MutableList<String>, view: ChipGroup) {
        list.forEach {
//            define View (chips) dynamically
            val chip = Chip(requireContext())
            val drawable = ChipDrawable.createFromAttributes(
                requireContext(), null, 0, R.style.DarkChip
            )
            chip.setChipDrawable(drawable)
            chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            chip.id = chipCounter++
            chip.text = it
            view.addView(chip)
        }
    }
    private fun updateChip(id: Int, view: ChipGroup) {
        if (id != 0) {
            view.findViewById<Chip>(id).isChecked = true
        }
    }
}