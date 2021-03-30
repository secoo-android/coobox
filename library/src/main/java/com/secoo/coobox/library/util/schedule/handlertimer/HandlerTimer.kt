package com.secoo.coobox.library.util.schedule.handlertimer

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * 使用 Handler 实现一个 Timer 定时器
 *
 */
class HandlerTimer {
    private var mCanceled = false

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == MESSAGE_REPEAT) {
                val task = msg.obj as? FixedRateTask
                task ?: return
                task.innerTask.run()
                if (!mCanceled) {
                    submitFixedRateTask(task)
                }
            }
        }
    }

    private fun submitFixedRateTask(fixedRateTask: FixedRateTask) {
        val message = Message.obtain(mHandler, MESSAGE_REPEAT).apply {
            this.obj = fixedRateTask
        }

        mHandler.sendMessageDelayed(message, fixedRateTask.intervalInMills)
    }

    /**
     * 以固定的频率执行任务
     * @param task 待执行的任务
     * @param intervalInMills 执行的频率 单位为ms
     *
     */
    fun scheduleAtFixedRate(task: Runnable?, intervalInMills: Long?) {
        task ?: return
        intervalInMills ?: return
        //取消正在等待的，规避一个Handler调用多次scheduleAtFixedRate导致混乱的问题
        mCanceled = false
        removePending()
        submitFixedRateTask(FixedRateTask(task, intervalInMills))
    }

    /**
     * 以固定的频率执行任务
     * @param task 待执行的任务
     * @param intervalInMills 执行的频率 单位为ms
     *
     */
    fun scheduleAtFixedRate(intervalInMills: Long?, task: (() -> Unit)? = null) {
        task ?: return
        scheduleAtFixedRate(Runnable {
            task.invoke()
        }, intervalInMills)
    }

    private fun removePending() {
        mHandler.removeCallbacksAndMessages(null)
    }

    /**
     * 取消定时器任务
     */
    fun cancel() {
        mCanceled = true
        removePending()
    }

    companion object {
        private const val MESSAGE_REPEAT = 0
    }
}
