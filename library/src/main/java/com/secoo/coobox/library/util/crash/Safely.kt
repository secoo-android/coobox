package com.secoo.coobox.library.util.crash

import com.secoo.coobox.library.ktx.kotlin.isNull

/**
 * 一个包装了结果和异常的数据类
 */
data class Result<out R>(val value: R?, val exception: Throwable?) {
    /**
     * 如果没有异常，则认为执行成功。并非代表得到了业务需要的值。
     */
    fun isSuccessful() = exception.isNull()
}

/**
 * 安全地执行获取操作
 * @param getAction 取值操作
 * @return 返回 Result,从中判断成功失败
 */
inline fun <R> getSafely(getAction: () -> R?): Result<R> {
    return try {
        Result(getAction(), null)
    } catch (t: Throwable) {
        t.printStackTrace()
        Result(null, t)
    }
}

/**
 * 安全地执行取值操作
 * @return 返回操作执行的值（可能为null，或非null）。如果有异常，返回null
 */
inline fun <R> getValueSafely(getValueAction: () -> R?): R? {
    return getSafely(getValueAction).value
}


/**
 * 安全执行block，捕获其中可能发生的异常
 * 如果没有异常，返回true，否则返回false
 */
inline fun runSafely(block: () -> Unit): Boolean {
    return getSafely(block).isSuccessful()
}