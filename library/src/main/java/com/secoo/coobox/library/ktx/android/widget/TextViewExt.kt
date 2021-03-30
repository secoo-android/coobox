package com.secoo.coobox.library.ktx.android.widget

import android.widget.TextView
import com.secoo.coobox.library.ktx.android.graphics.removeUnderline
import com.secoo.coobox.library.ktx.android.graphics.underline

/**
 * 设置 TextView 增加下划线样式
 */
fun TextView.underline() {
    paint?.underline()
}

/**
 * 移除 TextView 的下划线样式
 */
fun TextView.removeUnderline() = paint?.removeUnderline()


