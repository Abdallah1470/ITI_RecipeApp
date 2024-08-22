
import android.util.Log
import com.example.homerecipe.meals.model.ResponseMeal


class MealRepositoryImpl(private val remoteDataSource: MealRemote):
    MealRepository {

    override suspend fun getMealByCategoryName(name:String): ResponseMeal{
        val remote = remoteDataSource.getMealByCategoryName(name)
        Log.d("main", remote.toString())

        return remote
    }
}