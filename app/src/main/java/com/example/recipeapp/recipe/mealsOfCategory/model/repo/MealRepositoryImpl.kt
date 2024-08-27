
import android.util.Log
import com.example.recipeapp.recipe.mealsOfCategory.model.ResponseMeal
import com.example.recipeapp.recipe.model.Meal


class MealRepositoryImpl(private val remoteDataSource: MealRemote):
    MealRepository {

    override suspend fun getMealByCategoryName(name:String): List<Meal> {
        val remote = remoteDataSource.getMealByCategoryName(name).meals
        Log.d("main", remote.toString())

        return remote
    }
}