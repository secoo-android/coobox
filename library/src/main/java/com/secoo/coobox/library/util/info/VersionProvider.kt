package com.secoo.coobox.library.util.info

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build

/**
 * 版本数据提供者
 */
object VersionProvider {
    private const val FALLBACK_VERSION_CODE = -1L
    private val FALLBACK_VERSION_NAME = null

    private var appVersionCode = FALLBACK_VERSION_CODE
    private var appVersionName: String? = FALLBACK_VERSION_NAME

    /**
     * 获取版本号
     */
    fun getVersionCode(context: Context): Long {
        if (appVersionCode != FALLBACK_VERSION_CODE) {
            return appVersionCode
        }

        appVersionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getSelfPackageInfo(context)?.longVersionCode
        } else {
            getSelfPackageInfo(context)?.versionCode?.toLong()
        } ?: FALLBACK_VERSION_CODE
        return appVersionCode
    }

    /**
     * 获取版本名称
     */
    fun getVersionName(context: Context): String? {
        if (appVersionName != FALLBACK_VERSION_NAME) {
            return appVersionName
        }

        appVersionName = getSelfPackageInfo(context)?.versionName
        return appVersionName
    }

    private fun getSelfPackageInfo(context: Context): PackageInfo? {
        return context.packageManager.getPackageInfo(context.packageName, 0)
    }
}