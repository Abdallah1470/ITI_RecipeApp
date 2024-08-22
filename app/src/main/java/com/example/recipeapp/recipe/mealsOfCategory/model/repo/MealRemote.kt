import com.example.homerecipe.meals.model.ResponseMeal

interface MealRemote {
     suspend fun getMealByCategoryName(name:String) : ResponseMeal
}