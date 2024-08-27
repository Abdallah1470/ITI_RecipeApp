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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerecipe.meals.MealViewModelFactory
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.recipe.favorite.model.FavoriteDatabase
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.mealsOfCategory.viewModel.MealsViewModel
import com.example.recipeapp.recipe.network.MealsRequest

class DetalisFragment : Fragment() {
    private lateinit var mealsRecyclerView: RecyclerView

    private val args: DetalisFragmentArgs by navArgs()

    private val viewModel: MealsViewModel by viewModels() {
        val remote = MealRemoteImpl(MealsRequest)
        val favorite =
            FavoriteRepository(FavoriteDatabase.getInstance(requireContext()).favoriteDao())
        Log.d("main Details  fragment", args.categoryName)
        MealViewModelFactory(favorite, MealRepositoryImpl(remote), args.categoryName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_detalis, container, false)

        mealsRecyclerView = view.findViewById(R.id.recyclerViewMeals)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mealsListAdapter = MealsAdapter(viewModel, findNavController(), getUser())
        mealsRecyclerView.adapter = mealsListAdapter

        viewModel.fetchMeals()
        viewModel.meal.observe(viewLifecycleOwner) { meals ->
            mealsListAdapter.setData(meals)
            mealsRecyclerView.layoutManager = GridLayoutManager(
                requireContext(), getColumSpan(requireContext().applicationContext)
            )

        }
    }

    override fun onResume() {
        super.onResume()
        // Get the Activity Toolbar
        val actionBar = (activity as AppCompatActivity).supportActionBar

        // show the back arrow
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        // title for this fragment
        actionBar?.title = args.categoryName
    }


    // to get number of item to put in each row
    fun getColumSpan(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return dpWidth.div(180).toInt()
    }

    private fun getUser(): Int {
        return userSharedPreferences.getLong(USER_ID,-1).toInt()
    }


}