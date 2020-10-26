package com.melegy.redscreenofdeath.handler

import com.melegy.redscreenofdeath.listener.CrashListener
import com.melegy.redscreenofdeath.logger.Logger
import kotlin.system.exitProcess

internal class UncaughtExceptionHandler(private val crashListener: CrashListener) :
    Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, throwable: Throwable) = try {
        crashListener.onUncaughtException(thread, throwable)
    } catch (e: Exception) {
        Logger.logger.e("An error occurred in the uncaught exception handler", e)
    } finally {
        Logger.logger.d("RedDeathScreen completed exception processing.")
        exitProcess(1)
    }
}
