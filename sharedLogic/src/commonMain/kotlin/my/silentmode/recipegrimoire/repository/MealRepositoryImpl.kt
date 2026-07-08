package my.silentmode.recipegrimoire.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import my.silentmode.recipegrimoire.model.MealModel
import my.silentmode.recipegrimoire.model.MealResponse

class MealRepositoryImpl : MealRepository {
    private val baseURL = "https://themealdb.com/api/json/v1/1"
    private val searchURL = "$baseURL/search.php?s="
    private val httpClient = createHttpClient()

    override suspend fun fetchMeals(mealName: String): List<MealModel> {
        val response = httpClient.get("${searchURL}${mealName}")

        if (response.status == HttpStatusCode.OK) {
            val mealResponse = response.body<MealResponse>()
            return mealResponse.toModel()
        }

        return listOf()
    }

    override fun favorites(): Flow<List<MealModel>> {
        TODO("Not yet implemented")
    }
}