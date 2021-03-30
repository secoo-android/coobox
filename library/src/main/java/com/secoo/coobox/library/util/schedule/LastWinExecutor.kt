package com.secoo.coobox.library.util.schedule

import android.os.Handler
import android.os.Looper
import com.secoo.coobox.library.alias.type.KRunnable
/**
 * 任务执行器，在规定的时间内执行提交，最后一个会被执行，之前的会被取消
 */
class LastWinExecutor(private val timeSpanInMills: Long) {
    private val handler = Handler(Looper.getMainLooper())

    /**
     * 提交一个任务，如果在提交后 @param timeSpanInMills 没有新任务, 则执行该任务，否则被取消
     */
    fun submit(task: KRunnable) {
        clearAllTasks()
        handler.postDelayed(task, timeSpanInMills)
    }

    /**
     * 取消所有任务
     */
    fun clearAllTasks() {
        handler.removeCallbacksAndMessages(null)
    }
}