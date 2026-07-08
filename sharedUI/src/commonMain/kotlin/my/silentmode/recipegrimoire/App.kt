package my.silentmode.recipegrimoire

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import my.silentmode.recipegrimoire.model.UiState
import my.silentmode.recipegrimoire.presentation.MealViewModel

@Composable
fun App(viewModel: MealViewModel = MealViewModel()) {
    MaterialTheme {
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                    Text("Loading...")
                }

                is UiState.Error -> {
                    Text("Error: ${(uiState as UiState.Error).message}")
                }

                is UiState.Success -> {
                    val meals = (uiState as UiState.Success).data

                    meals.forEach { meal ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                                    .copy(alpha = 0.8f),
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    .copy(alpha = 0.8f),
                                disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
                                disabledContentColor = MaterialTheme.colorScheme.inversePrimary
                            )
                        ) {
                            Row {
                                AsyncImage(
                                    model = meal.thumbnail,
                                    contentDescription = meal.name,
                                    modifier = Modifier.size(64.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Column(
                                    modifier = Modifier.padding(
                                        vertical = 8.dp,
                                        horizontal = 16.dp
                                    )
                                ) {
                                    Text(
                                        meal.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Text(meal.country)
                                }
                            }
                        }
                    }
                }

                else -> {

                }
            }
        }
    }
}