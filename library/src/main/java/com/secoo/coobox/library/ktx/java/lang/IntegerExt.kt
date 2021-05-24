package com.secoo.coobox.library.ktx.java.lang

/**
 * 去当前 Int 的 一半值（Float）
 */
fun Int.halfFloat(): Float {
    return this / 2f
}

/**
 * 当前Int dp值转换px
 */
val Int.toPx
    get() = this.toFloat().toPx
