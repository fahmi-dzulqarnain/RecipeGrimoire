package my.silentmode.recipegrimoire

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform