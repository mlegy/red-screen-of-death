package com.melegy.redscreenofdeath.internal

internal fun interface CrashListener {
    fun onUncaughtException(thread: Thread, throwable: Throwable)
}
