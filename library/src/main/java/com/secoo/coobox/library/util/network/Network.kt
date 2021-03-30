package com.secoo.coobox.library.util.network

import android.content.Context
import android.net.ConnectivityManager

import android.net.NetworkInfo
import com.secoo.coobox.library.util.crash.getValueSafely


/**
 * 获取活动网路信息
 */
private fun Context.getActiveNetworkInfo(): NetworkInfo? {
    return getValueSafely {
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo
    }
}

/**
 * 判断当前网络是否可用
 */
fun Context?.isNetworkAvailable(): Boolean {
    return this?.getActiveNetworkInfo()?.isAvailable == true
}

/**
 * 判断当前网络是否不可用
 */
fun Context?.isNetworkNotAvailable() = this.isNetworkAvailable().not()