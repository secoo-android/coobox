package com.secoo.coobox.library.lifecycle

import android.app.Activity


object AppStateHelper {
    private var activityCount = 0
    private val listeners = mutableListOf<OnAppStateChangedListener>()

    /**
     * 判断 App 当前是否为前台可见状态
     */
    val isForeground: Boolean
        get() = activityCount != 0

    /**
     * 判断 App 当前是否为后台不可见状态
     */
    val isBackground: Boolean
        get() = activityCount == 0


    enum class Message {
        BACKGROUNDED, FOREGROUNDED
    }

    /**
     * 返回当前的App状态
     * @return
     */
    val state: Message
        get() = if (isForeground) {
            Message.FOREGROUNDED
        } else {
            Message.BACKGROUNDED
        }

    /**
     * Activity 执行到 onStart 时调用，需主动调用
     */
    fun activityStarting(activity: Activity): Boolean {
        var ret = false
        if (activityCount == 0) {
            onForeground(activity)
            ret = true
        }
        activityCount++
        return ret
    }

    /**
     * Activity 执行到 onStop 时调用，需主动调用
     * */
    @Synchronized
    fun activityStopping(activity: Activity): Boolean {
        activityCount--
        if (activityCount == 0) {
            onBackground(activity)
            return true
        }
        return false
    }

    fun addListener(listener: OnAppStateChangedListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: OnAppStateChangedListener) {
        listeners.remove(listener)
    }

    private fun onBackground(activity: Activity) {
        listeners.forEach {
            it.onAppStateChanged(Message.BACKGROUNDED, activity)
        }
    }

    private fun onForeground(activity: Activity) {
        listeners.forEach {
            it.onAppStateChanged(Message.FOREGROUNDED, activity)
        }
    }

}