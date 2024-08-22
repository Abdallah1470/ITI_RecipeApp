package com.example.homerecipe.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homerecipe.home.model.repo.CategoryRepository
import com.example.homerecipe.home.model.Category
import kotlinx.coroutines.launch

// take object from repository in constructor
class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    private val _data = MutableLiveData<List<Category>>()
    val data:LiveData<List<Category>> get() = _data

    fun fetchProducts() {
        viewModelScope.launch {
            _data.postValue(repository.getAllCategories())
            Log.d("main","${_data.value?.get(0)?.idCategory}")
        }
    }

}