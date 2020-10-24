package com.melegy.redscreenofdeath

internal fun interface CrashListener {
    fun onUncaughtException(thread: Thread, throwable: Throwable)
}
