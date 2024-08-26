package com.example.recipeapp.recipe.search.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.recipe.model.Meal
import com.example.recipeapp.recipe.search.viewmodel.SearchViewModel
import androidx.navigation.NavController
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory

class SearchAdapter(
    private val meals: List<MealsOfCategory>?,
    private val itemClickListener: OnItemClickListener,
/*    private val viewModel: SearchViewModel,
    private val userId: Long,*/
    private val navController: NavController
) : RecyclerView.Adapter<SearchAdapter.MyHolder>() {

    private var isChecked = false

    interface OnItemClickListener {
        fun onItemClick(meal: Meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_card, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return meals?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (meals?.isNotEmpty() == true) {
            val imageUrl = meals[position].strMealThumb
            if (imageUrl != null) {
                holder.showImage(imageUrl)
            }
            holder.setMealName(meals[position].strMeal.toString())

     /*       holder.addToFavorite.setOnClickListener {
                // checkbox and user database

            }*/
            holder.itemView.setOnClickListener {
                Log.d("Meal", "Meal id = ${meals[position].idMeal}")
                val action = SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(meals[position].idMeal)
                navController.navigate(action)
            }
        }
    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mealName: TextView = view.findViewById(R.id.recipe_name)
        private val mealImage: ImageView = view.findViewById(R.id.recipe_image)
//        val addToFavorite: CheckBox = view.findViewById(R.id.favorites_checkbox)

        fun setMealName(name: String) {
            mealName.text = name
        }

        fun showImage(url: String) {
            Glide.with(itemView.context).load(url).placeholder(R.drawable.ic_launcher_foreground).into(mealImage)
        }
    }
}