package my.silentmode.recipegrimoire.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import my.silentmode.recipegrimoire.model.MealModel
import my.silentmode.recipegrimoire.presentation.sampleMeal
import my.silentmode.recipegrimoire.repository.MealRepository

class FakeMealRepository: MealRepository {
    val favoritesFlow = MutableStateFlow<List<MealModel>>(listOf())

    override suspend fun fetchMeals(mealName: String): List<MealModel> {
        return listOf(sampleMeal)
    }

    override fun favorites(): Flow<List<MealModel>> {
        return favoritesFlow
    }

    override fun saveFavorite(meal: MealModel): Int {
        favoritesFlow.value += meal
        return 1
    }

    override fun removeFavorite(mealID: String): Int {
        favoritesFlow.value = favoritesFlow.value.filter { it.id != mealID }
        return 1
    }
}