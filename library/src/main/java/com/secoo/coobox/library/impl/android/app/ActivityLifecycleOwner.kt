package com.secoo.coobox.library.impl.android.app

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * 将Activity（非ComponentActivity）转换，使其获取Lifecycle
 * 利用ActivityLifecycleCallbacks 检测对应的生命周期，来自行实现 Lifecycle
 */
class ActivityLifecycleOwner(private val targetActivity: Activity) : ActivityLifecycleCallbacksImpl(), LifecycleOwner {
    private val lifecycleRegistry: LifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_CREATE)
    }

    override fun onActivityStarted(activity: Activity) {
        super.onActivityStarted(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_START)
    }

    override fun onActivityResumed(activity: Activity) {
        super.onActivityResumed(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_RESUME)
    }

    override fun onActivityPaused(activity: Activity) {
        super.onActivityPaused(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_PAUSE)
    }

    override fun onActivityStopped(activity: Activity) {
        super.onActivityStopped(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_STOP)
    }

    override fun onActivityDestroyed(activity: Activity) {
        super.onActivityDestroyed(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_DESTROY)

    }

    private fun handleLifecycleEvent(activity: Activity, event: Lifecycle.Event) {
        if (activity == targetActivity) {
            lifecycleRegistry.handleLifecycleEvent(event)
        }
    }




    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}