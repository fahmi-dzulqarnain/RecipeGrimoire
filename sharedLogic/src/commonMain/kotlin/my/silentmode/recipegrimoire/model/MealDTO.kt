package my.silentmode.recipegrimoire.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponse (
    @SerialName("meals")
    val meals: List<MealDTO>
){
    fun toModel(): List<MealModel> {
        return meals.map {
            MealModel(
                id = it.id,
                name = it.name,
                category = it.category,
                country = it.country,
                instructions = it.instructions,
                thumbnail = it.thumbnail,
                ingredient1 = it.ingredient1,
                measure1 = it.measure1,
                ingredient2 = it.ingredient2,
                measure2 = it.measure2,
                ingredient3 = it.ingredient3,
                measure3 = it.measure3
            )
        }
    }
}

@Serializable
data class MealDTO (
    @SerialName("idMeal")
    val id: String,
    @SerialName("strMeal")
    val name: String,
    @SerialName("strCategory")
    val category: String,
    @SerialName("strCountry")
    val country: String,
    @SerialName("strInstructions")
    val instructions: String,
    @SerialName("strMealThumb")
    val thumbnail: String,
    @SerialName("strIngredient1")
    val ingredient1: String,
    @SerialName("strMeasure1")
    val measure1: String,
    @SerialName("strIngredient2")
    val ingredient2: String,
    @SerialName("strMeasure2")
    val measure2: String,
    @SerialName("strIngredient3")
    val ingredient3: String,
    @SerialName("strMeasure3")
    val measure3: String
)
