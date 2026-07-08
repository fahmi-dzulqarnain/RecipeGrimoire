package my.silentmode.recipegrimoire.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import my.silentmode.recipegrimoire.model.UiState
import my.silentmode.recipegrimoire.repository.MealRepository
import my.silentmode.recipegrimoire.repository.MealRepositoryImpl

class MealViewModel : ViewModel() {
    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val mealRepository: MealRepository = MealRepositoryImpl()

    fun fetchMeals() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val meals = mealRepository.fetchMeals("chicken")
                uiState.value = UiState.Success(meals)
            } catch (ex: Exception) {
                uiState.value = UiState.Error(ex.message ?: "Unknown error")
            }
        }
    }
}