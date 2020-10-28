package com.melegy.redscreenofdeath.internal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class AppDate(
    val name: String,
    val versionName: String,
    val versionCode: String,
) : Parcelable
