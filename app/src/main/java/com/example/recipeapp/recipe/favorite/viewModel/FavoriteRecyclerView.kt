package com.example.recipeapp.recipe.favorite.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.recipe.favorite.FavoriteFragment
import com.example.recipeapp.recipe.favorite.FavoriteFragmentDirections
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory

data class FavoriteRecyclerView(
    private val meals: MutableList<MealsOfCategory>?,
    private val userId: Int,
    private val viewModel: FavoriteViewModel,
    private val navController: NavController
) :
RecyclerView.Adapter<FavoriteRecyclerView.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_row, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return meals?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.removeFromFavorite.setImageResource(R.drawable.lover)
        if (meals?.isNotEmpty() == true) {
            val imageUrl = meals[position].strMealThumb
            if (imageUrl != null) {
                holder.showImage(imageUrl)
            }
            holder.setMealName(meals[position].strMeal.toString())


            holder.removeFromFavorite.setOnClickListener {
                holder.removeFromFavorite.setImageResource(R.drawable.heart)
                viewModel.deleteFromFavorite(meals[position],userId)
                meals.removeAt(position)
                notifyItemRemoved(position)
            }
            holder.itemView.setOnClickListener {
                val action =
                    FavoriteFragmentDirections.actionFavoriteFragment2ToRecipeDetailFragment((meals[position].idMeal).toString())
                navController.navigate(action)
            }
        }
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val _mealImageView: ImageView = itemView.findViewById(R.id.mealImage)
        private val _mealName: TextView = itemView.findViewById(R.id.mealName)
        val removeFromFavorite: ImageView = itemView.findViewById(R.id.mealFavorite)


        fun showImage(imageUrl:String) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(_mealImageView)
        }

        fun setMealName(mealName: String) {
            this._mealName.text = mealName
        }

    }
}
