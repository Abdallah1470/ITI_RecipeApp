package com.example.recipeapp.recipe.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.recipe.home.model.repo.CategoryRemoteImpl
import com.example.homerecipe.home.model.repo.CategoryViewModelFactory
import com.example.recipeapp.recipe.home.model.repo.CategoryRepositoryImpl
import com.example.recipeapp.recipe.home.viewModel.HomeViewModel
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.auth.register.model.UserDatabase
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.recipe.favorite.model.FavoriteDatabase
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import com.example.recipeapp.recipe.network.MealsRequest
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var recommendedRecyclerView: RecyclerView
    private lateinit var shimmerRecommended: ShimmerFrameLayout
    private lateinit var shimmerCategory: ShimmerFrameLayout
    private lateinit var iconUser: ImageView
    private lateinit var userName: TextView
    private lateinit var recipeName: TextView
    private lateinit var recipeRandom: ImageView
    private lateinit var buttonDetails: Button


    private val viewModel: HomeViewModel by viewModels() {
        val remote = CategoryRemoteImpl(MealsRequest.service)
        val favorite =
            FavoriteRepository(FavoriteDatabase.getInstance(requireContext()).favoriteDao())
        val user = UserRepository(UserDatabase.getInstance(requireContext()).userDao())
        CategoryViewModelFactory(CategoryRepositoryImpl(remote), favorite, user)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initializationView(view)
        return view
    }

    private fun initializationView(view: View) {
        categoryRecyclerView = view.findViewById(R.id.recyclerViewHome)
        recommendedRecyclerView = view.findViewById(R.id.recyclerViewRecommended)
        shimmerCategory = view.findViewById(R.id.shimmer_view_container_category)
        shimmerRecommended = view.findViewById(R.id.shimmer_view_container_recommended)
        iconUser = view.findViewById(R.id.iconUserImage)
        userName = view.findViewById(R.id.nameUserTextView)
        recipeRandom = view.findViewById(R.id.recipe_image)
        buttonDetails = view.findViewById(R.id.buttonDetails)
        recipeName = view.findViewById(R.id.recipe_name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryListAdapter = HomeAdapter(findNavController())
        val recommendedListAdapter = RecommendedAdapter(findNavController(), viewModel, getUser())


        categoryRecyclerView.adapter = categoryListAdapter
        recommendedRecyclerView.adapter = recommendedListAdapter


        // infalte data in category recycler view
        viewModel.fetchProducts()
        viewModel.data.observe(viewLifecycleOwner) { data ->

            categoryListAdapter.setData(data)

            categoryRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            shimmerCategory.stopShimmer()
            shimmerCategory.visibility = View.GONE
            categoryRecyclerView.visibility = View.VISIBLE
        }

        // infalte data in recommended  recycler view
        viewModel.getMealRecommended()
        viewModel.recommendedMeal.observe(viewLifecycleOwner) { meal ->

            recommendedListAdapter.setData(meal)

            recommendedRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            shimmerRecommended.stopShimmer()
            shimmerRecommended.visibility = View.GONE
            recommendedRecyclerView.visibility = View.VISIBLE

        }

        // put meal best in home page
        viewModel.getRecipe()
        viewModel.recipeMeal.observe(viewLifecycleOwner) { meal ->
            meal.strMealThumb?.let { showImage(it, recipeRandom) }

            recipeName.text = meal.strMeal

            recipeRandom.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(meal.idMeal)
                findNavController().navigate(action)
            }

            buttonDetails.setOnClickListener{
                val action =
                    meal.strCategory?.let { it1 ->
                        HomeFragmentDirections.actionHomeFragmentToDetalisFragment(
                            it1
                        )
                    }
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
        }


        // put user data such image , name
        viewModel.getUserData(getUser())
        viewModel.userData.observe(viewLifecycleOwner) { user ->
            userName.text = " Hello, " + user?.name +"!"
            user?.profilePicture?.let { iconUser.setImageResource(it) }

        }
    }

    override fun onStart() {
        super.onStart()
        // Get the Activity Toolbar
        val actionBar = (activity as AppCompatActivity).supportActionBar

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
        actionBar?.hide()
    }


    private fun getUser(): Int {
        return userSharedPreferences.getLong(USER_ID, -1).toInt()
    }


    private fun showImage(imageUrl: String, itemView: ImageView) {
        Glide.with(itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(itemView)
    }


}
