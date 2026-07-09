package my.silentmode.recipegrimoire

import io.ktor.client.HttpClient
import my.silentmode.recipegrimoire.cache.AndroidDatabaseDriverFactory
import my.silentmode.recipegrimoire.cache.DatabaseDriverFactory
import my.silentmode.recipegrimoire.repository.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(get()) }
    single<HttpClient> { createHttpClient() }
}