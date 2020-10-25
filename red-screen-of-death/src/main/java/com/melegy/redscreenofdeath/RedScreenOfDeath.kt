package com.melegy.redscreenofdeath

import android.app.Application
import android.content.Context

object RedScreenOfDeath {
    @JvmStatic
    fun init(application: Application) {
        val crashListener = CrashListener { t, e -> handleUncaughtException(application, t, e) }
        val crashHandler = UncaughtExceptionHandler(crashListener)
        Thread.setDefaultUncaughtExceptionHandler(crashHandler)
    }

    private fun handleUncaughtException(context: Context, thread: Thread, throwable: Throwable) {
        val intent = RedScreenOfDeathActivity.newIntent(
            context = context,
            threadName = thread.name,
            throwable = throwable
        )
        context.startActivity(intent)
    }
}
