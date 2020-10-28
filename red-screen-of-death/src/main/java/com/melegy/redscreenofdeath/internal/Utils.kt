package com.melegy.redscreenofdeath.internal

import android.content.Context

internal object Utils {

    fun normalizeException(appData: AppDate, threadName: String, throwable: Throwable): String =
        "${appData.name} App crashed in $threadName\n" +
                "Version code: ${appData.versionCode}\n" +
                "Version name: ${appData.versionName}\n" +
                "Stack Trace: ${throwable.stackTraceToString()}"

    fun getAppData(context: Context): AppDate {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        val appName = packageManager.getApplicationLabel(context.applicationInfo).toString()
        val appVersionName = packageInfo.versionName
        val appVersionCode =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toString()
            } else {
                packageInfo.versionCode.toString()
            }
        return AppDate(name = appName, versionCode = appVersionCode, versionName = appVersionName)
    }
}
