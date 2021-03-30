package com.secoo.coobox.library.logger

import android.util.Log
import com.secoo.coobox.library.ktx.java.lang.displayName
import com.secoo.coobox.library.logger.LogAssistant.isLogEnabled
import com.secoo.coobox.library.logger.LogAssistant.logPrefix

object LogAssistant {
    var isLogEnabled = false
    var logPrefix = "LogAssistant"
}

/**
 * 执行日志输出
 * 该输出形式，规避了不必要的性能开销
 * 具体了解，请阅读 @see https://droidyue.com/blog/2019/11/24/smart-log-in-android-slash-kotlin/
 */
@JvmOverloads
inline fun Any.smartLogD(tag: String = "", lazyMessage: (() -> Any?)) {
    if (isLogEnabled) {
        Log.d("$logPrefix$tag", composeLogMessage(lazyMessage()))
    }
}

/**
 * 将对象转成 日志描述内容
 */
fun Any.composeLogMessage(vararg args: Any?): String {
    return "${this.javaClass.displayName};${args.joinToString(" ")}"
}

