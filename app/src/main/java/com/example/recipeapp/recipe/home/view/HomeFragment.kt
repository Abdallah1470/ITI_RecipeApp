package com.example.homerecipe.home.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.recipe.home.HomeRecycleView
import com.example.homerecipe.home.model.repo.CategoryViewModelFactory
import com.example.homerecipe.home.model.repo.CategoryRemoteDatabaseImpl
import com.example.homerecipe.home.model.repo.CategoryRepositoryImpl
import com.example.homerecipe.home.viewModel.CategoryViewModel
import com.example.recipeapp.R
import com.example.recipeapp.recipe.network.MealsRequest

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val viewModel: CategoryViewModel by viewModels(){
        val remote = CategoryRemoteDatabaseImpl(MealsRequest.service)
        CategoryViewModelFactory(CategoryRepositoryImpl(remote))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewHome)

        val navController = findNavController()

        viewModel.fetchProducts()
        viewModel.data.observe(viewLifecycleOwner) { data ->
            recyclerView.adapter = HomeRecycleView(data, navController)
            recyclerView.layoutManager = GridLayoutManager(requireContext(),getColumSpan(requireContext()))

        }
        return view
    }


    // to get number of item to put in each row
    fun getColumSpan(context: Context):Int{
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return dpWidth.div(180).toInt()
    }
}