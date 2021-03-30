package com.secoo.coobox.library.util.viewrect

import android.graphics.Rect
import android.view.View

/**
 * 视图 与 矩形区域 变化监听器
 */
interface OnViewChangedInRectListener {
    /**
     * 当视图出现在 目标矩形区域时 回调
     */
    fun onViewAppearedInRect(view: View, targetRect: Rect)

    /**
     * 当视图从 目标矩形区域 消失时回调
     */
    fun onViewDisappearedInRect(view: View, targetRect: Rect)
}