package com.secoo.coobox.library.util.os

import android.os.Build

/**
 * 是否为安卓8.0 Oreo
 */
fun isOreo(): Boolean {
    return Build.VERSION.SDK_INT == Build.VERSION_CODES.O
}

fun isAndroid8() = isOreo()

/**
 * 是否为 Android 10 及以后的版本
 */
fun isAndroid10OrLater() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q