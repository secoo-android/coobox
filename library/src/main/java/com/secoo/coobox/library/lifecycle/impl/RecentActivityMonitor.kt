package com.secoo.coobox.library.lifecycle.impl

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.secoo.coobox.library.impl.android.app.ActivityLifecycleCallbacksImpl
import com.secoo.coobox.library.ktx.kotlin.secondLastOrNull
import com.secoo.coobox.library.ktx.kotlin.secondOrNull

/**
 * 监测最近的 activities
 * 注意： 其中主要依赖于 Activity.onCreate 和 Activity.onDestroy。
 */
object RecentActivityMonitor: ActivityLifecycleCallbacksImpl() {
    private val recentCreatedActivities = mutableListOf<Activity>()

    /**
     * 初始化监测器，建议在 Application.onCreate 初始化，否则可能因延迟导致数据异常
     */
    fun setup(application: Application) {
        application.unregisterActivityLifecycleCallbacks(this)
        application.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)
        recentCreatedActivities.add(activity)
    }

    override fun onActivityDestroyed(activity: Activity) {
        super.onActivityDestroyed(activity)
        recentCreatedActivities.remove(activity)
    }

    /**
     * 获取最后一个创建的 Activity
     */
    fun last() = recentCreatedActivities.lastOrNull()

    /**
     * 获取第一个创建的 Activity
     */
    fun first() = recentCreatedActivities.firstOrNull()

    /**
     * 获取倒数第二个创建的 Activity
     */
    fun secondLast() = recentCreatedActivities.secondLastOrNull()

    /**
     * 获取第二个创建的 Activity
     */
    fun second() = recentCreatedActivities.secondOrNull()

    /**
     * 获取最近创建的 Activity 列表
     */
    fun getRecentCreatedActivityList(): List<Activity> {
        return recentCreatedActivities.toList()
    }
}