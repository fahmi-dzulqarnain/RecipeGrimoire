package my.silentmode.recipegrimoire.repository

import kotlinx.coroutines.flow.Flow
import my.silentmode.recipegrimoire.model.MealModel

class MealRepositoryImpl : MealRepository {
    override suspend fun fetchMeals(): List<MealModel> {
        TODO("Not yet implemented")
    }

    override fun favorites(): Flow<List<MealModel>> {
        TODO("Not yet implemented")
    }
}