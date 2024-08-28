package com.example.recipeapp.recipe.favorite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.recipe.favorite.model.FavoriteDatabase
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.favorite.model.FavoriteViewModelFactory
import com.example.recipeapp.recipe.favorite.viewModel.FavoriteViewModel
import com.example.recipeapp.recipe.mealsOfCategory.view.DetalisFragment

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val viewModel: FavoriteViewModel by viewModels(){
        FavoriteViewModelFactory(FavoriteRepository(FavoriteDatabase.getInstance(requireContext().applicationContext).favoriteDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        recyclerView = view.findViewById(R.id.favoriteRecyclerView)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val favoriteMeals = FavoriteAdapter(getUser(), viewModel, findNavController())
        recyclerView.adapter = favoriteMeals

        viewModel.getMealsFavorite(getUser())
        viewModel.favoriteLiveData.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                favoriteMeals.setData(data)
            }
            recyclerView.layoutManager =
                GridLayoutManager(
                    requireContext(),
                    DetalisFragment().getColumSpan(requireContext().applicationContext)
                )
        }
    }


    override fun onResume() {
        super.onResume()

        // Get the Activity Toolbar
        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.show()
        // Configure the ActionBar if it exists
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
            title = "Favorite"
        }
    }

    private fun getUser(): Int {
        return userSharedPreferences.getLong(USER_ID,-1).toInt()
    }
}
