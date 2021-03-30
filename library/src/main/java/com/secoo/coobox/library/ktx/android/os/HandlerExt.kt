package com.secoo.coobox.library.ktx.android.os

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.secoo.coobox.library.util.lifecycle.HandlerGuard

/**
 * 主线程Looper，提供给业务，避免多次创建
 */
val mainThreadHandler: Handler by lazy {
    Handler(Looper.getMainLooper())
}

/**
 * 延迟执行 runnable，默认使用主线程Handler
 */
fun delayRun(runnable: Runnable?, delayInMills: Long) {
    runnable ?: return
    mainThreadHandler.postDelayed(runnable, delayInMills)
}

/**
 * 延迟执行 runnable，默认使用主线程Handler,当hostActivity销毁时，自动清理该任务
 */
fun delayRun(runnable: Runnable?, delayInMills: Long, hostActivity: Activity?) {
    runnable ?: return
    HandlerGuard.watch(hostActivity, runnable, mainThreadHandler)
    mainThreadHandler.postDelayed(runnable, delayInMills)
}

/**
 * 延迟执行 runnable，默认使用主线程Handler,当hostFragment销毁时，自动清理该任务
 */
fun delayRun(runnable: Runnable?, delayInMills: Long, hostFragment: Fragment?) {
    runnable ?: return
    HandlerGuard.watch(hostFragment, runnable, mainThreadHandler)
    mainThreadHandler.postDelayed(runnable, delayInMills)
}


/**
 * 检测当前 Handler 是否为主线程 Handler
 */
fun Handler?.isMainThreadHandler(): Boolean {
    return this == mainThreadHandler
}

fun Handler?.toDescription(): String {
    return "ref=$this;isMainThreadHandler=${this.isMainThreadHandler()}"
}