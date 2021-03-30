package com.secoo.coobox.library.ktx.android.net

import android.net.Uri
import com.secoo.coobox.library.ktx.kotlin.isNotNullNorEmpty
import com.secoo.coobox.library.ktx.kotlin.toValidUri
import com.secoo.coobox.library.util.crash.getValueSafely

/**
 * 验证Uri是否合法，我们认为能够获得scheme不为null且不为空，则是合法的Uri
 */
fun Uri.isValid(): Boolean {
    return try {
        //如果能执行成功，则认为合法
        this.scheme.isNotNullNorEmpty()
    } catch (t: Throwable) {
        t.printStackTrace()
        false
    }
}

/**
 *  尝试在字符串（可能为Uri）中增加参数名和参数值
 */
fun String?.appendUrlParameter(paramName: String?, paramValue: String?): String? {
    return this.toValidUri()?.appendParameter(paramName, paramValue) ?: this
}

/**
 * 在Uri对象后增加参参数名和参数值
 */
fun Uri?.appendParameter(paramName: String?, paramValue: String?): String? {
    return getValueSafely {
        this?.buildUpon()?.appendQueryParameter(paramName, paramValue)?.build()?.toString()
    } ?: this?.toString()
}