
import com.example.homerecipe.meals.model.ResponseMeal
import com.example.recipeapp.recipe.network.MealsRequest

class MealRemoteImpl(private val apiService: MealsRequest ) : MealRemote {

    override suspend fun getMealByCategoryName(name:String ): ResponseMeal {
        return apiService.service.getMealsByCategory(name)
    }

}