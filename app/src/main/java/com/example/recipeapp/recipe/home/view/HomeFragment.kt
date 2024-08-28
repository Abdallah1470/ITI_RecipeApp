package com.example.recipeapp.recipe.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.recipe.home.model.repo.CategoryRemoteImpl
import com.example.homerecipe.home.model.repo.CategoryViewModelFactory
import com.example.recipeapp.recipe.home.model.repo.CategoryRepositoryImpl
import com.example.recipeapp.recipe.home.viewModel.HomeViewModel
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.recipe.favorite.model.FavoriteDatabase
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.network.MealsRequest
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var recommendedRecyclerView: RecyclerView
    private lateinit var shimmerRecommended: ShimmerFrameLayout
    private lateinit var shimmerCategory: ShimmerFrameLayout

    private val viewModel: HomeViewModel by viewModels() {
        val remote = CategoryRemoteImpl(MealsRequest.service)
        val favorite =
            FavoriteRepository(FavoriteDatabase.getInstance(requireContext()).favoriteDao())
        CategoryViewModelFactory(CategoryRepositoryImpl(remote),favorite)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        categoryRecyclerView = view.findViewById(R.id.recyclerViewHome)
        recommendedRecyclerView = view.findViewById(R.id.recyclerViewRecommended)
        shimmerCategory = view.findViewById(R.id.shimmer_view_container_category)
        shimmerRecommended = view.findViewById(R.id.shimmer_view_container_recommended)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryListAdapter = HomeAdapter(findNavController())
        val recommendedListAdapter = RecommendedAdapter(findNavController(),viewModel,getUser())


        categoryRecyclerView.adapter = categoryListAdapter
        recommendedRecyclerView.adapter = recommendedListAdapter

        viewModel.fetchProducts()
        viewModel.data.observe(viewLifecycleOwner) { data ->

            categoryListAdapter.setData(data)

            categoryRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            shimmerCategory.stopShimmer()
            shimmerCategory.visibility = View.GONE
            categoryRecyclerView.visibility = View.VISIBLE
        }

        viewModel.getMealRecommended()
        viewModel.recommendedMeal.observe(viewLifecycleOwner) { meal ->

            recommendedListAdapter.setData(meal)

            recommendedRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            shimmerRecommended.stopShimmer()
            shimmerRecommended.visibility = View.GONE
            recommendedRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        // Get the Activity Toolbar
        val actionBar = (activity as AppCompatActivity).supportActionBar

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
            title = "Favorite"
        }
        actionBar?.hide()
    }

    private fun getUser(): Int {
        return userSharedPreferences.getLong(USER_ID, -1).toInt()
    }
}
