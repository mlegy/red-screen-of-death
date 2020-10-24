package com.melegy.redscreenofdeath.sample

import android.app.Application
import com.melegy.redscreenofdeath.RedScreenOfDeath

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RedScreenOfDeath.init(this)
    }
}
