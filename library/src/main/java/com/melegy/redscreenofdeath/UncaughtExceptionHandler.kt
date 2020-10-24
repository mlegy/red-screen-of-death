package com.melegy.redscreenofdeath

internal class UncaughtExceptionHandler(
    private val crashListener: CrashListener,
    private val defaultHandler: Thread.UncaughtExceptionHandler?
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, throwable: Throwable) = try {
        crashListener.onUncaughtException(thread, throwable)
    } catch (e: Exception) {
        Logger.logger.e("An error occurred in the uncaught exception handler", e)
    } finally {
        Logger.logger.d("RedDeathScreen completed exception processing.")
        defaultHandler?.uncaughtException(thread, throwable)
    }
}
