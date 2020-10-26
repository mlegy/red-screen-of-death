package com.melegy.redscreenofdeath.sample

import android.app.Application
import com.melegy.redscreenofdeath.initRedScreenOfDeath

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Using Kotlin Extension over
        //RedScreenOfDeath.init(this)
        initRedScreenOfDeath()
    }
}
