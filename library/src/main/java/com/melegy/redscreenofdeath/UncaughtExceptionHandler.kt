package com.melegy.redscreenofdeath

internal class UncaughtExceptionHandler(private val crashListener: CrashListener) :
    Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread?, throwable: Throwable?) = try {
        when {
            thread == null ->
                Logger.logger.e("Could not handle uncaught exception; null thread")
            throwable == null ->
                Logger.logger.e("Could not handle uncaught exception; null throwable")
            else -> crashListener.onUncaughtException(thread, throwable)
        }
    } catch (e: Exception) {
        Logger.logger.e("An error occurred in the uncaught exception handler", e)
    } finally {
        Logger.logger.d("RedDeathScreen completed exception processing.")
    }
}
