package com.melegy.redscreenofdeath

import android.app.Application
import android.content.Context
import kotlin.system.exitProcess

object RedScreenOfDeath {
    fun init(application: Application) {
        val crashListener =
            CrashListener { thread, ex -> handleUncaughtException(application, thread, ex) }
        val crashHandler =
            UncaughtExceptionHandler(crashListener, Thread.getDefaultUncaughtExceptionHandler())
        Thread.setDefaultUncaughtExceptionHandler(crashHandler)
    }

    private fun handleUncaughtException(context: Context, thread: Thread, ex: Throwable) {
        context.startActivity(
            RedScreenOfDeathActivity.newIntent(
                context = context,
                threadName = thread.name,
                throwable = ex
            )
        )
        exitProcess(1)
    }
}
