package com.example.recipeapp.recipe.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.recipe.model.Meal

class RecommendedAdapter(private val navigation: NavController) :
    RecyclerView.Adapter<RecommendedAdapter.MyHolder>() {

    private var meals: List<Meal> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    fun setData(meal:List<Meal>){
        meals = meal
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (meals.isNotEmpty()) {
            val imageUrl = meals[position].strMealThumb
            if (imageUrl != null) {
                holder.showImage(imageUrl)
            }
            holder.setMealName(meals[position].strCategory.toString())
        }

        holder.itemView.setOnClickListener {
            val categoryItemName = meals[position].strCategory.toString()

            val action = HomeFragmentDirections.actionHomeFragmentToDetalisFragment(categoryItemName)
            navigation.navigate(action)
        }
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val _categoryImageView: ImageView = itemView.findViewById(R.id.mealImage)
        private val _categoryName: TextView = itemView.findViewById(R.id.categoryName)


        fun showImage(imageUrl:String) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(_categoryImageView)
        }

        fun setMealName(mealName:String){
            this._categoryName.text = mealName
        }

    }
}