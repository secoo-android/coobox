package com.secoo.coobox.library.impl.android.app

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * 将Activity（非ComponentActivity）转换，使其获取Lifecycle
 * 利用ActivityLifecycleCallbacks 检测对应的生命周期，来自行实现 Lifecycle
 */
class ActivityLifecycleOwnerV29(targetActivity: Activity) : ActivityLifecycleOwner(targetActivity),
    LifecycleOwner {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityPostCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityPostCreated(activity, savedInstanceState)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_CREATE)
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityPostStarted(activity: Activity) {
        super.onActivityPostStarted(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_START)
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPostResumed(activity: Activity) {
        super.onActivityPostResumed(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_RESUME)
    }

    override fun onActivityPrePaused(activity: Activity) {
        super.onActivityPrePaused(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_PAUSE)
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityPreStopped(activity: Activity) {
        super.onActivityPreStopped(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_STOP)
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityPreDestroyed(activity: Activity) {
        super.onActivityPreDestroyed(activity)
        handleLifecycleEvent(activity, Lifecycle.Event.ON_DESTROY)
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}