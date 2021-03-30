package com.secoo.coobox.library.util.pm

import android.content.Context
import android.content.Intent

/**
 * 是否有可以处理当前intent的activity 如果有一个或者多个返回true，否则为false
 */
fun Intent.hasResolvableActivity(context: Context): Boolean {
    return context.packageManager.resolveActivity(this, 0) != null
}