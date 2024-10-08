package com.example.recipeapp.recipe.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data class representing a Meal entity in the database.
 *
 * @property idMeal The unique identifier for the meal.
 * @property strArea The area or region where the meal is from.
 * @property strCategory The category of the meal.
 * @property strCreativeCommonsConfirmed Whether the meal is confirmed under Creative Commons.
 * @property strDrinkAlternate Alternate drink for the meal.
 * @property strImageSource The source of the meal image.
 * @property strIngredient1 The first ingredient of the meal.
 * @property strIngredient10 The tenth ingredient of the meal.
 * @property strIngredient11 The eleventh ingredient of the meal.
 * @property strIngredient12 The twelfth ingredient of the meal.
 * @property strIngredient13 The thirteenth ingredient of the meal.
 * @property strIngredient14 The fourteenth ingredient of the meal.
 * @property strIngredient15 The fifteenth ingredient of the meal.
 * @property strIngredient16 The sixteenth ingredient of the meal.
 * @property strIngredient17 The seventeenth ingredient of the meal.
 * @property strIngredient18 The eighteenth ingredient of the meal.
 * @property strIngredient19 The nineteenth ingredient of the meal.
 * @property strIngredient2 The second ingredient of the meal.
 * @property strIngredient20 The twentieth ingredient of the meal.
 * @property strIngredient3 The third ingredient of the meal.
 * @property strIngredient4 The fourth ingredient of the meal.
 * @property strIngredient5 The fifth ingredient of the meal.
 * @property strIngredient6 The sixth ingredient of the meal.
 * @property strIngredient7 The seventh ingredient of the meal.
 * @property strIngredient8 The eighth ingredient of the meal.
 * @property strIngredient9 The ninth ingredient of the meal.
 * @property strInstructions The instructions for preparing the meal.
 * @property strMeal The name of the meal.
 * @property strMealThumb The thumbnail image of the meal.
 * @property strMeasure1 The measurement for the first ingredient.
 * @property strMeasure10 The measurement for the tenth ingredient.
 * @property strMeasure11 The measurement for the eleventh ingredient.
 * @property strMeasure12 The measurement for the twelfth ingredient.
 * @property strMeasure13 The measurement for the thirteenth ingredient.
 * @property strMeasure14 The measurement for the fourteenth ingredient.
 * @property strMeasure15 The measurement for the fifteenth ingredient.
 * @property strMeasure16 The measurement for the sixteenth ingredient.
 * @property strMeasure17 The measurement for the seventeenth ingredient.
 * @property strMeasure18 The measurement for the eighteenth ingredient.
 * @property strMeasure19 The measurement for the nineteenth ingredient.
 * @property strMeasure2 The measurement for the second ingredient.
 * @property strMeasure20 The measurement for the twentieth ingredient.
 * @property strMeasure3 The measurement for the third ingredient.
 * @property strMeasure4 The measurement for the fourth ingredient.
 * @property strMeasure5 The measurement for the fifth ingredient.
 * @property strMeasure6 The measurement for the sixth ingredient.
 * @property strMeasure7 The measurement for the seventh ingredient.
 * @property strMeasure8 The measurement for the eighth ingredient.
 * @property strMeasure9 The measurement for the ninth ingredient.
 * @property strSource The source of the meal recipe.
 * @property strTags Tags associated with the meal.
 * @property strYoutube The YouTube link for the meal preparation video.
 */
@Entity(tableName = "meals")
data class Meal(
    @SerializedName("idMeal")
    @PrimaryKey(autoGenerate = false)
    val idMeal: String,
    @SerializedName("strArea") val strArea: String?,
    @SerializedName("strCategory") val strCategory: String?,
    @SerializedName("strCreativeCommonsConfirmed") val strCreativeCommonsConfirmed: String?,
    @SerializedName("strDrinkAlternate") val strDrinkAlternate: String?,
    @SerializedName("strImageSource") val strImageSource: String?,
    @SerializedName("strIngredient1") val strIngredient1: String?,
    @SerializedName("strIngredient10") val strIngredient10: String?,
    @SerializedName("strIngredient11") val strIngredient11: String?,
    @SerializedName("strIngredient12") val strIngredient12: String?,
    @SerializedName("strIngredient13") val strIngredient13: String?,
    @SerializedName("strIngredient14") val strIngredient14: String?,
    @SerializedName("strIngredient15") val strIngredient15: String?,
    @SerializedName("strIngredient16") val strIngredient16: String?,
    @SerializedName("strIngredient17") val strIngredient17: String?,
    @SerializedName("strIngredient18") val strIngredient18: String?,
    @SerializedName("strIngredient19") val strIngredient19: String?,
    @SerializedName("strIngredient2") val strIngredient2: String?,
    @SerializedName("strIngredient20") val strIngredient20: String?,
    @SerializedName("strIngredient3") val strIngredient3: String?,
    @SerializedName("strIngredient4") val strIngredient4: String?,
    @SerializedName("strIngredient5") val strIngredient5: String?,
    @SerializedName("strIngredient6") val strIngredient6: String?,
    @SerializedName("strIngredient7") val strIngredient7: String?,
    @SerializedName("strIngredient8") val strIngredient8: String?,
    @SerializedName("strIngredient9") val strIngredient9: String?,
    @SerializedName("strInstructions") val strInstructions: String?,
    @SerializedName("strMeal") val strMeal: String?,
    @SerializedName("strMealThumb") val strMealThumb: String?,
    @SerializedName("strMeasure1") val strMeasure1: String?,
    @SerializedName("strMeasure10") val strMeasure10: String?,
    @SerializedName("strMeasure11") val strMeasure11: String?,
    @SerializedName("strMeasure12") val strMeasure12: String?,
    @SerializedName("strMeasure13") val strMeasure13: String?,
    @SerializedName("strMeasure14") val strMeasure14: String?,
    @SerializedName("strMeasure15") val strMeasure15: String?,
    @SerializedName("strMeasure16") val strMeasure16: String?,
    @SerializedName("strMeasure17") val strMeasure17: String?,
    @SerializedName("strMeasure18") val strMeasure18: String?,
    @SerializedName("strMeasure19") val strMeasure19: String?,
    @SerializedName("strMeasure2") val strMeasure2: String?,
    @SerializedName("strMeasure20") val strMeasure20: String?,
    @SerializedName("strMeasure3") val strMeasure3: String?,
    @SerializedName("strMeasure4") val strMeasure4: String?,
    @SerializedName("strMeasure5") val strMeasure5: String?,
    @SerializedName("strMeasure6") val strMeasure6: String?,
    @SerializedName("strMeasure7") val strMeasure7: String?,
    @SerializedName("strMeasure8") val strMeasure8: String?,
    @SerializedName("strMeasure9") val strMeasure9: String?,
    @SerializedName("strSource") val strSource: String?,
    @SerializedName("strTags") val strTags: String?,
    @SerializedName("strYoutube") val strYoutube: String?
)