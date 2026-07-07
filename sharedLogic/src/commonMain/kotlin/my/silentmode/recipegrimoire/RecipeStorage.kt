package my.silentmode.recipegrimoire

expect class RecipeStorage() {
    fun saveFavourite(recipeID: String)
    fun removeFavourite(recipeID: String)
    fun isFavourite(recipeID: String): Boolean
    fun getFavourites(): List<String>
}