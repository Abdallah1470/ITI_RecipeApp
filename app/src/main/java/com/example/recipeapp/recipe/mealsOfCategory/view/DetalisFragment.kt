package com.example.recipeapp.recipe.mealsOfCategory.view

import MealRemoteImpl
import MealRepositoryImpl
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerecipe.meals.MealViewModelFactory
import com.example.homerecipe.meals.view.MealsRecyclerView
import com.example.homerecipe.meals.viewModel.MealsViewModel
import com.example.recipeapp.R
import com.example.recipeapp.recipe.home.view.HomeFragment
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory
import com.example.recipeapp.recipe.network.MealsRequest

class DetalisFragment : Fragment(), MealsRecyclerView.OnItemClickListener {
    private lateinit var mealsRecyclerView: RecyclerView
    private val args: DetalisFragmentArgs by navArgs()

    private val viewModel: MealsViewModel by viewModels() {
        val remote = MealRemoteImpl(MealsRequest)
        Log.d("main Details  fragment", args.categoryName)
        MealViewModelFactory(MealRepositoryImpl(remote), args.categoryName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalis, container, false)

        mealsRecyclerView = view.findViewById(R.id.recyclerViewMeals)

        viewModel.fetchMeals()
        viewModel.meal.observe(viewLifecycleOwner) { meals ->
            mealsRecyclerView.adapter = MealsRecyclerView(this, meals)
            mealsRecyclerView.layoutManager = GridLayoutManager(
                requireContext(), getColumSpan(requireContext().applicationContext)
            )

        }
        return view
    }

    override fun onItemClick(meal: MealsOfCategory) {
        findNavController().navigate(
            DetalisFragmentDirections.actionDetalisFragmentToRecipeDetailFragment(
                meal.idMeal
            )
        )
    }

    // to get number of item to put in each row
    fun getColumSpan(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return dpWidth.div(180).toInt()
    }

}