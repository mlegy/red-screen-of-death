package com.melegy.redscreenofdeath.internal

import kotlin.system.exitProcess

internal class UncaughtExceptionHandler(private val crashListener: CrashListener) :
    Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, throwable: Throwable) = try {
        crashListener.onUncaughtException(thread, throwable)
    } catch (e: Exception) {
        Logger.logger.e("An error occurred in the uncaught exception handler", e)
    } finally {
        Logger.logger.d("Red Screen of Death completed exception processing.")
        exitProcess(1)
    }
}
