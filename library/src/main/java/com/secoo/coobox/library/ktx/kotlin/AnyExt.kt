package com.secoo.coobox.library.ktx.kotlin

/**
 * 将一种类型转换为另一种类型,如果类型转换不允许，返回null
 */
inline fun <reified T> Any.asType(): T? {
    return if (this is T) {
        this
    } else {
        null
    }
}

/**
 * 获取对象的 HashCode（字符串类型）
 */
fun Any?.hashCodeString(): String? {
    return this?.hashCode()?.toString()
}

/**
 * 判断当前对应，是否为 null
 */
fun Any?.isNull(): Boolean {
    return this == null
}

/**
 * 判断当前对象 不为 null
 */
fun Any?.isNotNull(): Boolean {
    return this != null
}