package com.melegy.redscreenofdeath

import android.util.Log

/** Default logger that logs to android.util.Log.  */
class Logger(private val tag: String) {
    private val logLevel: Int = Log.INFO

    private fun canLog(level: Int): Boolean {
        return logLevel <= level || Log.isLoggable(tag, level)
    }

    fun d(text: String, throwable: Throwable? = null) {
        if (canLog(Log.DEBUG)) {
            Log.d(tag, text, throwable)
        }
    }

    fun e(text: String, throwable: Throwable? = null) {
        if (canLog(Log.ERROR)) {
            Log.e(tag, text, throwable)
        }
    }

    companion object {
        private const val TAG = "RedScreenOfDeath"

        /** Returns the global [Logger].  */
        val logger = Logger(TAG)
    }
}
