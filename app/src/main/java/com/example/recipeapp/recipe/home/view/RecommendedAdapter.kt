package com.example.recipeapp.recipe.home.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.recipe.home.viewModel.HomeViewModel
import com.example.recipeapp.recipe.mealsOfCategory.view.DetalisFragmentDirections
import com.example.recipeapp.recipe.mealsOfCategory.viewModel.MealsViewModel
import com.example.recipeapp.recipe.model.Meal

private var isChecked: Boolean = false

class RecommendedAdapter(private val navController: NavController, private val viewModel: HomeViewModel,private val userID:Int
) :
    RecyclerView.Adapter<RecommendedAdapter.MyHolder>() {

    private var meals: List<Meal> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_row, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    fun setData(meal: List<Meal>) {
        meals = meal
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (meals.isNotEmpty()) {
            val imageUrl = meals[position].strMealThumb
            if (imageUrl != null) {
                holder.showImage(imageUrl)
            }
            holder.setMealName(meals[position].strMeal.toString())

            holder.addToFavorite.setOnClickListener {
                isChecked = !isChecked
                if (isChecked) {
                    holder.addToFavorite.setImageResource(R.drawable.lover)
                    viewModel.insertToFavorite(meals[position], userID)
                } else {
                    holder.addToFavorite.setImageResource(R.drawable.heart)
                    viewModel.deleteFromFavorite(meals[position], userID)
                }

            }
            holder.itemView.setOnClickListener {
                Log.d("Meal", "Meal id = ${meals[position].idMeal}")
                val action =
                    HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment((meals[position].idMeal))
                navController.navigate(action)
            }
        }

    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val _mealImageView: ImageView = itemView.findViewById(R.id.mealImage)
        private val _mealName: TextView = itemView.findViewById(R.id.mealName)
        val addToFavorite: ImageView = itemView.findViewById(R.id.mealFavorite)


        fun showImage(imageUrl:String) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(_mealImageView)
        }

        fun setMealName(mealName: String){
            this._mealName.text = mealName
        }

    }
}