package my.silentmode.recipegrimoire.cache

actual fun createDriverFactory(): DatabaseDriverFactory {
    return AndroidDatabaseDriverFactory(AndroidContextHolder.applicationContext)
}