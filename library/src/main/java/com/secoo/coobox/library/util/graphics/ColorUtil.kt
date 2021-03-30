package com.secoo.coobox.library.util.graphics

import android.graphics.Color
import com.secoo.coobox.library.util.crash.getValueSafely

const val NUMBER_SIGN_COLOR_CHAR = '#'

/**
 * 获取色值对应的alpha
 */
fun alphaOfColor(colorString: String?): Int? {
    return if (colorString == null) {
        null
    } else {
        getValueSafely {
            Color.alpha(Color.parseColor(colorString))
        }
    }
}

/**
 * 是否为透明色
 */
fun isTransparentColor(colorString: String?): Boolean {
    return getValueSafely {
        alphaOfColor(colorString) == 0
    } ?: false
}

/**
 * 是否为合法的Color 字符串
 */
fun isValidColorString(colorString: String?): Boolean {
    return if (colorString.isNullOrBlank()) {
        false
    } else {
         parseColorSafely(colorString) != null
    }
}

/**
 * 智能转换colorString
 * 能够处理 #cce8cf 和 cce8cf（不含#）的情况
 */
fun parseColorSmartly(colorString: String?): Int? {
    if (isValidColorString(colorString)) {
        return parseColorSafely(colorString)
    }
    /**
     * 不能直接为不含有#的字符串增加#,因为parseColor内部有检测类似`red`这样的单词查找色值的。
     * 所以需要在上一次检测失败后，根据色值字符串是否包含#再添加
     */

    val firstChar = colorString?.firstOrNull() ?: return null

    if (firstChar != NUMBER_SIGN_COLOR_CHAR) {
        val fixedColorString = appendNumberSignColorPrefix(colorString)
        if (isValidColorString(fixedColorString)) {
            return parseColorSafely(fixedColorString)
        }
    }
    return null
}

/**
 * 安全地转换并获取color，否则为null
 */
private fun parseColorSafely(colorString: String?): Int? {
    return getValueSafely {
        Color.parseColor(colorString)
    }
}

/**
 * 为color增加 # 前缀
 */
private fun appendNumberSignColorPrefix(colorString: String?): String? {
    return if (colorString.isNullOrEmpty()) {
        null
    } else {
        "$NUMBER_SIGN_COLOR_CHAR$colorString"
    }
}