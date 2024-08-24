package com.example.recipeapp.recipe.detailsMeal

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homerecipe.home.model.repo.CategoryRemoteImpl
import com.example.homerecipe.home.model.repo.CategoryViewModelFactory
import com.example.recipeapp.R
import com.example.recipeapp.recipe.detailsMeal.model.MealDetailsViewModelFactory
import com.example.recipeapp.recipe.detailsMeal.model.MealRepo
import com.example.recipeapp.recipe.detailsMeal.model.MealRepoImpl
import com.example.recipeapp.recipe.detailsMeal.model.RemoteDataImpl
import com.example.recipeapp.recipe.detailsMeal.viewModel.MealDetailsViewModel
import com.example.recipeapp.recipe.home.model.repo.CategoryRepositoryImpl
import com.example.recipeapp.recipe.home.viewModel.CategoryViewModel
import com.example.recipeapp.recipe.network.MealsRequest


var isChecked=false
class DetailsMealFragment : Fragment() {

    lateinit var imageView: ImageView
    lateinit var mealName: TextView
    lateinit var mealCategory: TextView
    lateinit var mealDesc: TextView
    lateinit var mealLink: TextView
    lateinit var hidenData: View
    lateinit var showMore :Button

    private val args:DetailsMealFragmentArgs by navArgs()

    private val viewModel: MealDetailsViewModel by viewModels(){
        val remote = RemoteDataImpl(MealsRequest.service)
        MealDetailsViewModelFactory(MealRepoImpl(remote))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details_meal, container, false)

        imageView = view.findViewById(R.id.mealImageView)
        mealName = view.findViewById(R.id.mealName)
        mealCategory = view.findViewById(R.id.maleCategory)
        mealDesc = view.findViewById(R.id.mealDesc)
        mealLink = view.findViewById(R.id.mealYoutubeLink)
        hidenData = view.findViewById(R.id.hidenDetails)

        showMore = view.findViewById(R.id.moreDetails)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.fetchMeal(args.mealId)
        viewModel.meal.observe(viewLifecycleOwner){meal ->
            loadImage(meal.strMealThumb)
            mealName.text = meal.strMeal
            mealDesc.text = meal.strInstructions
            mealLink.text = meal.strYoutube
            showMore.setOnClickListener {
                if (!isChecked) {
                    hidenData.visibility = View.VISIBLE
                    showMore.text =R.string.show_less.toString()
                    isChecked = true
                } else {
                    hidenData.visibility = View.GONE
                    showMore.text = R.string.show_more.toString()
                    isChecked=false
                }
            }
        }
    }

    private fun loadImage(link:String?){
        Glide.with(requireContext())
            .load(link)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }

}

