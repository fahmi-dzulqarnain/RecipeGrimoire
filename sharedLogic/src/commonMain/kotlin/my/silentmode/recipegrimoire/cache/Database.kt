package my.silentmode.recipegrimoire.cache

import my.silentmode.recipegrimoire.model.MealModel

internal class Database(driverFactory: DatabaseDriverFactory) {
    private val database = RecipeDatabase(driverFactory.createDriver())
    private val dbQuery = database.recipeDatabaseQueries

    fun insertMeal(meal: MealModel) {
        dbQuery.insertMeal(
            meal.id, meal.name, meal.category,
            meal.country, meal.instructions, meal.thumbnail,
            meal.ingredient1, meal.measure1,
            meal.ingredient2, meal.measure2,
            meal.ingredient3, meal.measure3
        )
    }

    fun getMeals(): List<Meal> {
        return dbQuery.selectAllMealsInfo().executeAsList()
    }
}