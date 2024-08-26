import com.example.recipeapp.recipe.mealsOfCategory.model.ResponseMeal

interface MealRemote {
     suspend fun getMealByCategoryName(name:String) : ResponseMeal
}