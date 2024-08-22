package com.example.homerecipe.meals
import MealRepositoryImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homerecipe.meals.viewModel.MealsViewModel

class MealViewModelFactory(private val repository: MealRepositoryImpl , private val name:String):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealsViewModel::class.java)){
            return MealsViewModel(repository,name) as T
        }else{
            throw IllegalArgumentException("ViewModel is not found ")
        }
    }
}