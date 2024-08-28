package com.example.recipeapp.recipe.search.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentSearchBinding
import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.network.MealsRequest
import com.example.recipeapp.recipe.search.model.repo.RecipesRepoImpl
import com.example.recipeapp.recipe.search.model.repo.RemoteDataSourceImpl
import com.example.recipeapp.recipe.search.viewmodel.SearchViewModel
import com.example.recipeapp.recipe.search.viewmodel.SearchViewModelFactory

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels() {
        SearchViewModelFactory(
            RecipesRepoImpl(
                RemoteDataSourceImpl(MealsRequest.service), requireContext()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            when {
                binding.searchEditText.text.toString().isEmpty() -> {
                    binding.searchRecyclerView.visibility = View.GONE
                    binding.lottieSearch.visibility = View.VISIBLE
                    binding.lottieNoData.visibility = View.GONE
                }

                searchResults.isNullOrEmpty() -> {
                    binding.searchRecyclerView.visibility = View.GONE
                    binding.lottieSearch.visibility = View.GONE
                    binding.lottieNoData.visibility = View.VISIBLE
                }

                else -> {
                    binding.searchRecyclerView.visibility = View.VISIBLE
                    binding.lottieSearch.visibility = View.GONE
                    binding.lottieNoData.visibility = View.GONE
                    binding.searchRecyclerView.layoutManager =
                        GridLayoutManager(requireContext(), 2)
                    binding.searchRecyclerView.adapter =
                        SearchAdapter(searchResults, this, findNavController())
                }
            }
        }

        binding.searchEditText.addTextChangedListener { editable ->
            val query = editable.toString()
            viewModel.searchRecipes(query)
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
        actionBar?.title = "Search"
    }

    override fun onItemClick(meal: Meal) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(meal.idMeal)
        findNavController().navigate(action)
    }
}