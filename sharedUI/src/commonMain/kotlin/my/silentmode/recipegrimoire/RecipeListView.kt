package my.silentmode.recipegrimoire

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import my.silentmode.recipegrimoire.model.MealModel
import my.silentmode.recipegrimoire.model.UiState

@Composable
fun RecipeListView(
    uiState: UiState,
    favoriteIDs: Set<String>,
    onFavoriteClick: () -> Unit,
    onClick: (MealModel) -> Unit
) {
    when (uiState) {
        is UiState.Loading -> {
            CircularProgressIndicator()
            Text("Loading...")
        }

        is UiState.Error -> {
            Text("Error: ${uiState.message}")
        }

        is UiState.Success -> {
            val meals = uiState.data

            LazyColumn {
                meals.forEach { meal ->
                    item {
                        RecipeItemView(
                            meal,
                            isFavorite = favoriteIDs.contains(meal.id),
                            onFavoriteClick,
                            onClick
                        )
                    }
                }
            }
        }
    }
}