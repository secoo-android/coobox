package com.secoo.coobox.library.util.schedule

import com.secoo.coobox.library.alias.type.KRunnable


/**
 * 早鸟（早起的鸟儿有虫吃）执行器, 在对应的时间周期内，只执行第一个提交的任务
 */
class  EarlyBirdExecutor(private val period: Long) {
    var lastWonTime = 0L

    /**
     * 提交任务
     * 如果被执行，返回true，否则视为被丢弃，返回false
     */
    fun submit(task: KRunnable): Boolean {
        val currentTime = System.currentTimeMillis()
        return if (currentTime - lastWonTime > period) {
            task.invoke()
            lastWonTime = currentTime
            true
        } else {
            false
        }
    }
}