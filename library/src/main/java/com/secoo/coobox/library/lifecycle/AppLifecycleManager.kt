package com.secoo.coobox.library.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.secoo.coobox.library.ktx.android.app.asFragmentActivity
import java.lang.ref.WeakReference

/**
 * 应用生命周期管理器
 */
object AppLifecycleManager {
    private var currentActivityWeakRef: WeakReference<Activity>? = null
    private val activityCallbacks = mutableMapOf<Int, List<Application.ActivityLifecycleCallbacks>>()

    /**
     * 为对应的 Activity 增加 生命周期回调
     */
    fun observeActivityCallback(activity: Activity, callback: Application.ActivityLifecycleCallbacks) {
        activityCallbacks[activity.hashCode()] = getActivityCallback(activity).plus(callback)
    }

    private fun getActivityCallback(activity: Activity?): List<Application.ActivityLifecycleCallbacks> {
        return if (activity == null) {
            listOf()
        } else {
            activityCallbacks[activity.hashCode()] ?: listOf()
        }
    }


    private fun removeActivityCallbacks(activity: Activity?): Boolean {
        return if (activity == null) {
            false
        } else {
            activityCallbacks.remove(activity.hashCode()) != null
        }
    }

    /**
     * 获取当前的 Activity
     */
    fun getCurrentActivity(): Activity? = currentActivityWeakRef?.get()

    /**
     * 获取当前的 FragmentActivity (由 Activity 进行类型转换得到)
     */
    fun getCurrentFragmentActivity(): FragmentActivity? = getCurrentActivity()?.asFragmentActivity()

    /**
     * 注册 Application，并处理响应的回调
     */
    fun registerApplication(application: Application) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
                getActivityCallback(activity).forEach {
                    if (activity != null) {
                        it.onActivityPaused(activity)
                    }
                }
            }

            override fun onActivityResumed(activity: Activity?) {
                activity?.run {
                    currentActivityWeakRef = WeakReference(activity)
                    getActivityCallback(activity).forEach {
                        it.onActivityResumed(activity)
                    }
                }
            }

            override fun onActivityStarted(activity: Activity?) {
                getActivityCallback(activity).forEach {
                    if (activity != null) {
                        it.onActivityStarted(activity)
                    }
                }
            }

            override fun onActivityDestroyed(activity: Activity?) {
                getActivityCallback(activity).forEach {
                    if (activity != null) {
                        it.onActivityDestroyed(activity)
                    }
                }
                removeActivityCallbacks(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                getActivityCallback(activity).forEach {
                    if (activity != null && outState != null) {
                        it.onActivitySaveInstanceState(activity, outState)
                    }
                }
            }

            override fun onActivityStopped(activity: Activity?) {
                getActivityCallback(activity).forEach {
                    if (activity != null) {
                        it.onActivityStopped(activity)
                    }
                }
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                getActivityCallback(activity).forEach {
                    if (activity != null) {
                        it.onActivityCreated(activity, savedInstanceState)
                    }
                }
            }

        })
    }
}
