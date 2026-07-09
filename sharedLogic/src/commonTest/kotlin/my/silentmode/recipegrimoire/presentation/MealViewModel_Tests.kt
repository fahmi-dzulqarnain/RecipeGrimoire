package my.silentmode.recipegrimoire.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import my.silentmode.recipegrimoire.domain.FakeMealRepository
import my.silentmode.recipegrimoire.model.MealModel
import my.silentmode.recipegrimoire.repository.MealRepository
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

val sampleMeal = MealModel(
    id = "1",
    name = "Test Meal",
    category = "Test Category",
    country = "Test Land",
    instructions = "Do the test",
    thumbnail = "https://example.com/something.jpg",
    ingredient1 = "Ingredient 1", measure1 = "1 tsp",
    ingredient2 = "Ingredient 2", measure2 = "1 tsp",
    ingredient3 = "Ingredient 3", measure3 = "1 tsp"
)

@OptIn(ExperimentalCoroutinesApi::class)
class MealViewModel_Tests : KoinTest {
    private val testDispatcher = StandardTestDispatcher()
    private val mealRepository: MealRepository = FakeMealRepository()
    private val viewModel: MealViewModel by inject()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules (
                module {
                    single<MealRepository> { mealRepository }
                    factory { MealViewModel(get()) }
                }
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun test_toggleFavorite_addMeals() = runTest(testDispatcher) {
        // Arrange
        val secondSampleMeal = sampleMeal.copy(id = "2")
        val collectedStates = mutableListOf<Set<String>>()
        val collectJob = launch {
            viewModel.favoriteIDs.collect {
                collectedStates.add(it)
            }
        }

        // Assert 1
        assertTrue(viewModel.favoriteIDs.value.isEmpty())

        // Act 1
        viewModel.toggleFavorite(sampleMeal)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert 2
        assertEquals(1, viewModel.favoriteIDs.value.size)

        // Act 2
        viewModel.toggleFavorite(secondSampleMeal)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert 3
        assertEquals(2, viewModel.favoriteIDs.value.size)

        collectJob.cancel()
    }
}