package com.secoo.coobox.library.ktx.java.util

import com.secoo.coobox.library.util.crash.getValueSafely

/**
 * 安全地根据 Key 获取 HashMap 中对应的 值
 */
fun <K, V> HashMap<K, V>?.getSafely(key: K?): V? {
    return getValueSafely {
        this?.get(key)
    }
}