package my.silentmode.recipegrimoire

import my.silentmode.recipegrimoire.cache.Database
import my.silentmode.recipegrimoire.presentation.MealViewModel
import my.silentmode.recipegrimoire.repository.MealRepository
import my.silentmode.recipegrimoire.repository.MealRepositoryImpl
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
    single<MealRepository> { MealRepositoryImpl(get(), get()) }
    single { Database(get()) }
    factory { MealViewModel(get()) }
}

expect val platformModule: Module

fun initKoin(appDeclaration: KoinAppDeclaration): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(sharedModule, platformModule)
    }
}