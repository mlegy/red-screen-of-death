package com.melegy.redscreenofdeath

import android.app.Application
import android.content.Context
import com.melegy.redscreenofdeath.handler.UncaughtExceptionHandler
import com.melegy.redscreenofdeath.listener.CrashListener
import com.melegy.redscreenofdeath.ui.RedScreenOfDeathActivity

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
