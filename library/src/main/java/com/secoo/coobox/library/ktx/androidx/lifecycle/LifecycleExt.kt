package com.secoo.coobox.library.ktx.androidx.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.secoo.coobox.library.alias.type.KRunnable

/**
 * 在 Lifecycle onCreate 事件时 执行操作
 * @param oneShot true 代表只执行一次，false 为每次都执行
 */
internal fun Lifecycle.doOnCreate(oneShot: Boolean, runnable: KRunnable) {
    doOnEvent(Lifecycle.Event.ON_CREATE, oneShot, runnable)
}

/**
 * 在 Lifecycle onStart 事件时 执行操作
 * @param oneShot true 代表只执行一次，false 为每次都执行
 */
internal fun Lifecycle.doOnStart(oneShot: Boolean, runnable: KRunnable) {
    doOnEvent(Lifecycle.Event.ON_START, oneShot, runnable)
}

/**
 * 在 Lifecycle onResume 事件时 执行操作
 * @param oneShot true 代表只执行一次，false 为每次都执行
 */
internal fun Lifecycle.doOnResume(oneShot: Boolean, runnable: KRunnable) {
    doOnEvent(Lifecycle.Event.ON_RESUME, oneShot, runnable)
}

/**
 * 在 Lifecycle onPause 事件时 执行操作
 * @param oneShot true 代表只执行一次，false 为每次都执行
 */
internal fun Lifecycle.doOnPause(oneShot: Boolean, runnable: KRunnable) {
    doOnEvent(Lifecycle.Event.ON_PAUSE, oneShot, runnable)
}

/**
 * 在 Lifecycle onStop 事件时 执行操作
 * @param oneShot true 代表只执行一次，false 为每次都执行
 */
internal fun Lifecycle.doOnStop(oneShot: Boolean, runnable: KRunnable) {
    doOnEvent(Lifecycle.Event.ON_STOP, oneShot, runnable)
}

/**
 * 在 Lifecycle onDestroy 事件时 执行操作
 * @param oneShot true 代表只执行一次，false 为每次都执行
 */
internal fun Lifecycle.doOnDestroy(oneShot: Boolean, runnable: KRunnable) {
    doOnEvent(Lifecycle.Event.ON_DESTROY, oneShot, runnable)
}

/**
 * 检测 Lifecycle 的事件，并执行操作 一次或者多次
 * @param eventToObserve  调用者感兴趣的 Lifecycle 事件
 * @param oneShot true 代表只执行一次，false 为每次都执行
 * @param runnable 满足条件时，执行的操作
 */
private fun Lifecycle.doOnEvent(eventToObserve: Lifecycle.Event, oneShot: Boolean, runnable: () -> Unit) {
    addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, currentEvent: Lifecycle.Event) {
            if (eventToObserve == currentEvent) {
                runnable()
                // If it's expected to run only once; we need to remove the observer
                if (oneShot) {
                    removeObserver(this)
                }
            }

            // remove the observer to avoid memory leaks
            if (currentEvent == Lifecycle.Event.ON_DESTROY) {
                removeObserver(this)
            }
        }
    })
}