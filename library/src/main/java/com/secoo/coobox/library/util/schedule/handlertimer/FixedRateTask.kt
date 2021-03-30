package com.secoo.coobox.library.util.schedule.handlertimer

/**
 * 固定频率执行的任务
 * @param innerTask 重复执行的任务
 * @param intervalInMills 固定的频率，毫秒为单位
 */
data class FixedRateTask(val innerTask: Runnable, val intervalInMills: Long)

