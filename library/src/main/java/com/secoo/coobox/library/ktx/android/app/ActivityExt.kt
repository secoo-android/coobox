package com.secoo.coobox.library.ktx.android.app

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.secoo.coobox.library.alias.type.ActivityToUnit
import com.secoo.coobox.library.impl.android.app.ActivityLifecycleOwner
import com.secoo.coobox.library.ktx.androidx.lifecycle.*
import com.secoo.coobox.library.ktx.kotlin.asType
import com.secoo.coobox.library.lifecycle.AppLifecycleManager

/**
 * 获取Activity 绑定的 Lifecycle
 * 如果当前Activity是 ComponentActivity 则返回自带的 Lifecycle, 否则返回自行实现获取的 Lifecycle
 *
 */
private fun Activity.getBoundLifecycle(): Lifecycle {
    return if (this is ComponentActivity) {
        this.lifecycle
    }  else {
        val lifecycleOwner = ActivityLifecycleOwner(this)
        AppLifecycleManager.observeActivityCallback(this, lifecycleOwner)
        lifecycleOwner.lifecycle
    }
}

/**
 * 在 Activity onStart 时执行一次操作
 */
fun Activity.doOnStartOnce(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnStart(true) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onStart 时每次都执行操作
 */
fun Activity.doOnStartAlways(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnStart(false) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onResume 时执行一次操作
 */
fun Activity.doOnResumeOnce(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnResume(true) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onResume 时每次都执行操作
 */
fun Activity.doOnResumeAlways(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnResume(false) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onPause 时 执行一次操作
 */
fun Activity.doOnPauseOnce(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnPause(true) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onPause 时每次都执行操作
 */
fun Activity.doOnPauseAlways(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnPause(false) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onStop 时执行一次操作
 */
fun Activity.doOnStopOnce(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnStop(true) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onStop时 每次都执行操作
 */
fun Activity.doOnStopAlways(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnStop(false) {
        runnable.invoke(this)
    }
}

/**
 * 在 Activity onDestroy 时执行操作
 */
fun Activity.doOnDestroy(runnable: ActivityToUnit) {
    getBoundLifecycle().doOnDestroy(true) {
        runnable.invoke(this)
    }
}

/**
 * 尝试将当前的Activity转成FragmentActivity，如果失败，返回null
 */
fun Activity.asFragmentActivity(): FragmentActivity? {
    return this.asType<FragmentActivity>()
}

