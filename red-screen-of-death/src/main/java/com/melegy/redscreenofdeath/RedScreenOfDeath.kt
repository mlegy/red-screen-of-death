package com.melegy.redscreenofdeath

import android.app.Application
import android.content.Context
import com.melegy.redscreenofdeath.internal.CrashListener
import com.melegy.redscreenofdeath.internal.RedScreenOfDeathActivity
import com.melegy.redscreenofdeath.internal.UncaughtExceptionHandler
import com.melegy.redscreenofdeath.internal.Utils

object RedScreenOfDeath {
    @JvmStatic
    fun init(application: Application) {
        val crashListener = CrashListener { t, e -> handleUncaughtException(application, t, e) }
        val crashHandler = UncaughtExceptionHandler(crashListener)
        Thread.setDefaultUncaughtExceptionHandler(crashHandler)
    }

    private fun handleUncaughtException(context: Context, thread: Thread, throwable: Throwable) {
        val appData = Utils.getAppData(context)
        val intent = RedScreenOfDeathActivity.newIntent(
            context = context,
            threadName = thread.name,
            throwable = throwable,
            appData = appData,
        )
        context.startActivity(intent)
    }
}
