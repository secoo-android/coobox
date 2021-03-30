package com.secoo.coobox.library.ktx.android.content

import android.app.Activity
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.secoo.coobox.library.util.crash.getValueSafely
import com.secoo.coobox.library.util.crash.runSafely

/**
 * 尝试 将 Context 对象转换成 Activity 对象
 * @return 如果类型匹配，则返回对应的 Activity 对象，否则为null
 */
tailrec fun Context?.activity(): Activity? = when (this) {
    null -> null
    is Application -> null
    is Activity -> this
    else -> (this as? ContextWrapper)?.baseContext?.activity()
}

/**
 * 通知栏权限是否可以使用
 */
fun Context?.areNotificationsEnabled(): Boolean {
    this ?: return false
    return getValueSafely {
        NotificationManagerCompat.from(this).areNotificationsEnabled()
    } ?: false
}

/**
 * 复制内容到剪切板
 */
fun Context?.copyTextToClipboard(text: CharSequence?, label: CharSequence? = null): Boolean {
    this ?: return false
    return runSafely {
        this.getSystemService<ClipboardManager>()?.let {
            val clipData = ClipData.newPlainText(label ?: "copy", text)
            it.setPrimaryClip(clipData)
        }
    }
}
