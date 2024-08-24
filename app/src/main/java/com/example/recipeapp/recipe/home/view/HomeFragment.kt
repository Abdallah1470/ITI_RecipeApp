package com.example.recipeapp.recipe.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerecipe.home.model.repo.CategoryRemoteImpl
import com.example.homerecipe.home.model.repo.CategoryViewModelFactory
import com.example.recipeapp.recipe.home.model.repo.CategoryRepositoryImpl
import com.example.recipeapp.recipe.home.viewModel.CategoryViewModel
import com.example.recipeapp.R
import com.example.recipeapp.recipe.network.MealsRequest

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recommenddRecyclerView: RecyclerView

    private val viewModel: CategoryViewModel by viewModels(){
        val remote = CategoryRemoteImpl(MealsRequest.service)
        CategoryViewModelFactory(CategoryRepositoryImpl(remote))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewHome)
        recommenddRecyclerView = view.findViewById(R.id.recyclerViewRecommended)

        val navController = findNavController()

        viewModel.fetchProducts()
        viewModel.data.observe(viewLifecycleOwner) { data ->
            recyclerView.adapter = HomeRecycleView(data, navController)
            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            recommenddRecyclerView.adapter = HomeRecycleView(data, navController)
            recommenddRecyclerView.layoutManager =
                GridLayoutManager(requireContext(),2)

        }
        return view
    }
}