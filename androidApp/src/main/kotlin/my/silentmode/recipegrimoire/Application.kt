package my.silentmode.recipegrimoire

import android.app.Application
import my.silentmode.recipegrimoire.cache.AndroidContextHolder

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidContextHolder.applicationContext = this
    }
}