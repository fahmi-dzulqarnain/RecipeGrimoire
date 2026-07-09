package my.silentmode.recipegrimoire.cache

actual fun createDriverFactory(): DatabaseDriverFactory = IOSDatabaseDriverFactory()
