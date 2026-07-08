package my.silentmode.recipegrimoire

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.silentmode.recipegrimoire.model.UiState
import my.silentmode.recipegrimoire.presentation.MealViewModel
import org.jetbrains.compose.resources.painterResource

import recipegrimoire.sharedui.generated.resources.Res
import recipegrimoire.sharedui.generated.resources.compose_multiplatform

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
            Text("No Recipe Data Synced Yet!")

            Button(
                onClick = { viewModel.fetchMeals() },
                modifier = Modifier.padding()
            ) {
                Text("Sync Now")
            }
            if (uiState is UiState.Loading) {
                Text("Loading...")
            } else if (uiState is UiState.Error) {
                Text("Error: ${(uiState as UiState.Error).message}")
            } else if (uiState is UiState.Success) {
                val meals = (uiState as UiState.Success).data

                meals.forEach { meal ->
                    Text(meal.name)
                }
            } else {

            }
        }
    }
}