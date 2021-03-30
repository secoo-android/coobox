package com.secoo.coobox.library.ktx.kotlin

import android.net.Uri
import android.text.TextUtils
import androidx.core.net.toUri
import com.secoo.coobox.library.ktx.android.net.isValid
import com.secoo.coobox.library.util.crash.getValueSafely

/**
 * 删除字符串内部的换行符
 */
fun String?.removeLineBreaks(): String? {
    return this?.replace("\n", "")
}

/**
 * 检测该字符串转成Uri是否合法
 */
fun String?.isValidUriString(): Boolean {
    this ?: return false
    return getValueSafely {
        toUri().isValid()
    } ?: false
}

/**
 * 将字符串转换成合法的Uri
 * @return Uri对象，如果字符串不合法，返回null
 */
fun String?.toValidUri(): Uri? {
    return if (this?.isValidUriString() == true) {
        this.toUri()
    } else {
        null
    }
}

/**
 * 检测字符串，即不是null，也不是空内容字符串("")
 * @return 如果字符串既不是null，也不是空内容，返回true，否则为false
 */
fun String?.isNotNullNorEmpty(): Boolean {
    return !TextUtils.isEmpty(this)
}

/**
 * 提供当字符串为null时的一个替补值（默认为空字符串）
 * @return 如果当前字符串不为null，则返回值为当前字符串，否则为 fallback 参数值
 */
fun String?.fallback(fallback: String = ""): String {
    return this ?: fallback
}
