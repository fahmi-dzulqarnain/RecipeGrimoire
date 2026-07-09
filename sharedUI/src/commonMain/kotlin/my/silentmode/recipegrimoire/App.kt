package my.silentmode.recipegrimoire

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import my.silentmode.recipegrimoire.model.MealModel
import my.silentmode.recipegrimoire.model.UiState
import my.silentmode.recipegrimoire.presentation.MealViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun App(viewModel: MealViewModel = MealViewModel()) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Recipe Grimoire")
                    }
                )
            }
        ) { innerPadding ->
            var selectedMeal by remember { mutableStateOf<MealModel?>(null) }

            if (selectedMeal == null) {
                val favoriteIDs by viewModel.favoriteIDs.collectAsState()
                val uiState by viewModel.uiState.collectAsState()

                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    RecipeListView(
                        uiState = uiState,
                        favoriteIDs = favoriteIDs,
                        onFavoriteClick = {
                            viewModel.toggleFavorite(selectedMeal!!)
                        },
                        onClick = {
                            selectedMeal = it
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

        }
    }
}