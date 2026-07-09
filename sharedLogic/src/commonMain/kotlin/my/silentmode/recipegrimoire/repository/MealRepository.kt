package my.silentmode.recipegrimoire.repository

import kotlinx.coroutines.flow.Flow
import my.silentmode.recipegrimoire.model.MealModel

interface MealRepository {
    suspend fun fetchMeals(mealName: String): List<MealModel>
    fun favorites(): Flow<List<MealModel>>
    fun saveFavorite(meal: MealModel)
}
