package com.example.recipeapp.recipe.favorite.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.recipe.favorite.viewModel.FavoriteViewModel
import com.example.recipeapp.recipe.model.Meal

data class FavoriteAdapter(
    private val userId: Int,
    private val viewModel: FavoriteViewModel,
    private val navController: NavController
) :
RecyclerView.Adapter<FavoriteAdapter.MyHolder>() {
    private var meals: List<Meal> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_row, parent, false)
        return MyHolder(view)
    }

    fun setData(meal: List<Meal>) {
        this.meals = meal
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.removeFromFavorite.setImageResource(R.drawable.lover)
        if (meals.isNotEmpty()) {
            val imageUrl = meals[position].strMealThumb
            if (imageUrl != null) {
                holder.showImage(imageUrl)
            }
            holder.setMealName(meals[position].strMeal.toString())


            holder.removeFromFavorite.setOnClickListener {
                holder.removeFromFavorite.setImageResource(R.drawable.heart)
                viewModel.deleteFromFavorite(meals[position],userId)
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
