package com.example.homerecipe.meals.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.recipe.mealsOfCategory.model.MealsOfCategory

class MealsRecyclerView(
    private val onItemClickListener: OnItemClickListener, private val meals: List<MealsOfCategory>?
) : RecyclerView.Adapter<MealsRecyclerView.MyHolder>() {

    interface OnItemClickListener {
        fun onItemClick(meal: MealsOfCategory)
    }

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

            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(meals[position])
            }

            holder.addToFavorite.setOnClickListener {
                holder.addToFavorite()
            }
        }

    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val _mealImageView: ImageView = itemView.findViewById(R.id.mealImage)
        private val _mealName: TextView = itemView.findViewById(R.id.mealName)
        val addToFavorite: ImageView = itemView.findViewById(R.id.mealFavorite)


        fun showImage(imageUrl: String) {
            Glide.with(itemView.context).load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground).into(_mealImageView)
        }

        fun setMealName(mealName: String) {
            this._mealName.text = mealName
        }

        fun addToFavorite() {
            addToFavorite.setOnClickListener {
                // function add to favorit database
            }
        }
    }
}