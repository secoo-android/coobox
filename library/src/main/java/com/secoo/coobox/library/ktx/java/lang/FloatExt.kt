package com.secoo.coobox.library.ktx.java.lang

import android.content.res.Resources
import android.util.TypedValue

/**
 * 去当前 Float 的 一半
 */
fun Float.half(): Float {
    return this / 2
}

/**
 * 当前Float dp值转换px
 */
val Float.toPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
