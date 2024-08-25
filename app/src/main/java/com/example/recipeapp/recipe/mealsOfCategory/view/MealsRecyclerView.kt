package com.example.recipeapp.recipe.mealsOfCategory.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.recipe.mealsOfCategory.viewModel.MealsViewModel
import com.example.recipeapp.R
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory

private var isChecked:Boolean = false
class MealsRecyclerView(
    private val meals: List<MealsOfCategory>?,
    private val viewModel: MealsViewModel,
    private val userId:Int,
    private val navController: NavController
) :
    RecyclerView.Adapter<MealsRecyclerView.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_row, parent, false)
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

            holder.addToFavorite.setOnClickListener {
                isChecked = !isChecked
                if (isChecked) {
                    holder.addToFavorite.setImageResource(R.drawable.lover)
                    viewModel.insertToFavorite(meals[position],userId)
                } else {
                    holder.addToFavorite.setImageResource(R.drawable.heart)
                    viewModel.deleteFromFavorite(meals[position],userId)
                }

            }
            holder.itemView.setOnClickListener {
                Log.d("Meal","Meal id = ${meals.get(position).idMeal}")
                val action = DetalisFragmentDirections.actionDetalisFragmentToRecipeDetailFragment((meals[position].idMeal).toString())
                navController.navigate(action)
            }
        }

    }


    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val _mealImageView: ImageView = itemView.findViewById(R.id.mealImage)
        private val _mealName: TextView = itemView.findViewById(R.id.mealName)
        val addToFavorite: ImageView = itemView.findViewById(R.id.mealFavorite)


        fun showImage(imageUrl:String) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(_mealImageView)
        }

        fun setMealName(mealName:String){
            this._mealName.text = mealName
        }

    }
}