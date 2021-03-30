package com.secoo.coobox.library.util

import com.google.gson.Gson
import com.secoo.coobox.library.util.crash.getValueSafely

/**
 * 一个公用的 gson 对象，避免重复创建的开销问题
 */
val gson = Gson()

/**
 * 将 json 字符串转换成 对象
 * 如果转换正常，则返回对应类型的实例，否则为 null
 */
fun <T> json2Obj(json: String?, classOfT: Class<T>?): T? {
    json ?: return null
    classOfT ?: return null

    return getValueSafely {
        gson.fromJson(json, classOfT)
    }
}

/**
 * 将任意对象转成json字符串形式标识，如果有异常或者对象为null，返回null
 */
fun Any?.toJson(): String? {
    this ?: return null
    return getValueSafely {
        gson.toJson(this)
    }
}

