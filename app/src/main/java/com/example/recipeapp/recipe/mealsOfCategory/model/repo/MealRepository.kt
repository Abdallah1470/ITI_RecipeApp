import com.example.recipeapp.recipe.mealsOfCategory.model.ResponseMeal
import com.example.recipeapp.recipe.model.Meal

interface MealRepository {
     suspend fun getMealByCategoryName(name:String) : List<Meal>?
}