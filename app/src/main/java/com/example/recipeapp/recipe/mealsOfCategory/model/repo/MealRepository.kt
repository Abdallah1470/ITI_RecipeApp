import com.example.homerecipe.meals.model.ResponseMeal

interface MealRepository {
     suspend fun getMealByCategoryName(name:String) : ResponseMeal?
}