package com.secoo.coobox.library.ktx.android.graphics

import android.graphics.Paint
import com.secoo.coobox.library.ktx.support.addFlag
import com.secoo.coobox.library.ktx.support.removeFlag

/**
 * 设置展示文字袋中间划线
 */
fun Paint.strikeThrough() = this.also {
    flags = flags.addFlag(Paint.STRIKE_THRU_TEXT_FLAG)
}

/**
 * 恢复Paint设置
 */
fun Paint.removeStrikeThrough() = this.also {
    flags = flags.removeFlag(Paint.STRIKE_THRU_TEXT_FLAG)
}

/**
 * 设置 Paint 增加下划线
 */
fun Paint.underline() = this.also {
    flags = flags.addFlag(Paint.UNDERLINE_TEXT_FLAG)
}

/**
 * 设置 Paint 删除下划线
 */
fun Paint.removeUnderline() = this.also {
    flags = flags.removeFlag(Paint.UNDERLINE_TEXT_FLAG)
}