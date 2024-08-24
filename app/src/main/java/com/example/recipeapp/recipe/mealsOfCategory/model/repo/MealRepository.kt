import com.example.recipeapp.recipe.mealsOfCategory.model.ResponseMeal

interface MealRepository {
     suspend fun getMealByCategoryName(name:String) : ResponseMeal?
}