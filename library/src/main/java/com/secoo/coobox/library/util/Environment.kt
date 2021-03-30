package com.secoo.coobox.library.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import com.secoo.coobox.library.util.crash.getValueSafely
import java.io.File

/**
 * app是否可以调试
 */
fun Context.isAppDebuggable(): Boolean {
    return 0 != (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)
}

/**
 * 是否为可疑的模拟器
 */
fun isProbablyAnEmulator(): Boolean {
    return getValueSafely {
        Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.BOARD == "QC_Reference_Phone" //bluestacks
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.HOST.startsWith("Build") //MSI App Player
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk" == Build.PRODUCT
    } ?: false
}

/**
 * 判断当前设备是否已经获取 Root权限
 */
fun Context?.isRooted(): Boolean {
    return getValueSafely {
        val isEmulator: Boolean = isProbablyAnEmulator()
        val buildTags = Build.TAGS
        if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
            true
        } else {
            var file = File("/system/app/Superuser.apk")
            if (file.exists()) {
                true
            } else {
                file = File("/system/xbin/su")
                !isEmulator && file.exists()
            }
        }
    } ?: false
}