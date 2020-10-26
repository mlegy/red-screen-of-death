package com.melegy.redscreenofdeath.listener

internal fun interface CrashListener {
    fun onUncaughtException(thread: Thread, throwable: Throwable)
}
