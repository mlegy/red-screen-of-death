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

    fun v(text: String, throwable: Throwable? = null) {
        if (canLog(Log.VERBOSE)) {
            Log.v(tag, text, throwable)
        }
    }

    fun i(text: String, throwable: Throwable? = null) {
        if (canLog(Log.INFO)) {
            Log.i(tag, text, throwable)
        }
    }

    fun w(text: String, throwable: Throwable? = null) {
        if (canLog(Log.WARN)) {
            Log.w(tag, text, throwable)
        }
    }

    fun e(text: String, throwable: Throwable? = null) {
        if (canLog(Log.ERROR)) {
            Log.e(tag, text, throwable)
        }
    }

    fun log(priority: Int, msg: String, forceLog: Boolean = false) {
        if (forceLog || canLog(priority)) {
            Log.println(priority, tag, msg)
        }
    }

    companion object {
        private const val TAG = "RedScreenOfDeath"

        /** Returns the global [Logger].  */
        val logger = Logger(TAG)
    }
}
