package com.example.recipeapp.recipe.detail.view

import android.graphics.drawable.Drawable
import com.example.recipeapp.recipe.detail.viewmodel.RecipeDetailViewModelFactory
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.databinding.FragmentRecipeDetailBinding
import com.example.recipeapp.recipe.detail.viewmodel.RecipeDetailViewModel
import com.example.recipeapp.recipe.favorite.model.FavoriteDatabase
import com.example.recipeapp.recipe.favorite.model.FavoriteRepository
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment() {
    private val args: RecipeDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeDetailBinding

    private val viewModel: RecipeDetailViewModel by viewModels {
        val userDao = FavoriteDatabase.getInstance(requireContext()).favoriteDao()
        RecipeDetailViewModelFactory(FavoriteRepository(userDao))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchMealDetails(args.recipeId)

        viewModel.meal.observe(viewLifecycleOwner) { meal ->
            meal?.let {
                binding.recipeTitle.text = it.strMeal
                binding.recipeCategory.text = it.strCategory
                binding.recipeArea.text = it.strArea
                binding.recipeInstructions.text = it.strInstructions
                if (!it.strMealThumb.isNullOrBlank()) {
                    Glide.with(requireContext()).load(it.strMealThumb)
                        .placeholder(R.drawable.ic_launcher_foreground).into(binding.recipeImage)
                }
                viewModel.setupYouTubeVideo(it.strYoutube)
                viewModel.setIngredientsSection(meal)
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner){
            binding.favoritesCheckbox.isChecked = it
        }

        binding.favoritesCheckbox.apply {
            lifecycleScope.launch {
                viewModel.updateFavoriteStatus(isChecked,getUser(),args.recipeId)
            }
        }
        binding.favoritesCheckbox.apply {
            lifecycleScope.launch {
                viewModel.inFavorites(
                    getUser(),
                    args.recipeId
                )
                isChecked = viewModel.isFavorite.value ?: false
            }
            setOnCheckedChangeListener { button, isChecked ->
                viewModel.updateFavoriteStatus(
                    isChecked, getUser(),args.recipeId
                )
            }
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.youtubeUrl.observe(viewLifecycleOwner) { url ->
            url?.let { loadYouTubeVideo(it) }
        }

        viewModel.ingredient.observe(viewLifecycleOwner) {
            binding.recipeIngredients.text = it
        }

        binding.showMoreBtn.setOnClickListener {
            val isVisible = binding.constraintLayout.visibility == View.VISIBLE
            binding.constraintLayout.visibility = if (isVisible) View.GONE else View.VISIBLE
            binding.showMoreBtn.text =
                getString(if (isVisible) R.string.show_more else R.string.show_less)
        }
    }

    private fun loadYouTubeVideo(url: String) {
        binding.webView.apply {
            webViewClient = WebViewClient() // Ensures the video is loaded within the WebView
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }

    private fun getUser(): Int {
        return userSharedPreferences.getLong(USER_ID,-1).toInt()
    }

    override fun onStart() {
        super.onStart()
        // Get the Activity Toolbar
        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
        // Configure the ActionBar if it exists
        actionBar?.apply {
            title = "About"
        }

    }
}
