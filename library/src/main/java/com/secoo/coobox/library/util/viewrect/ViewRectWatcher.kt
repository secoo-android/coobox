package com.secoo.coobox.library.util.viewrect

import android.graphics.Rect
import android.view.View
import com.secoo.coobox.library.ktx.android.view.getVisibleRect
import com.secoo.coobox.library.logger.smartLogD

/**
 * 视图与 矩形区域 监视器
 * @param view 目标视图
 * @param targetRect 目标矩形区域
 */
class ViewRectWatcher(private val view: View, private val targetRect: Rect) {
    private var isIntersected: Boolean = false

    private val onViewRectChangedListeners = mutableListOf<OnViewChangedInRectListener>()

    /**
     * 添加监听器
     */
    fun addOnViewRectChangedListener(listener: OnViewChangedInRectListener) {
        onViewRectChangedListeners.add(listener)
    }

    /**
     * 移除监听器
     */
    fun removeOnViewRectChangedListener(listener: OnViewChangedInRectListener) {
        onViewRectChangedListeners.remove(listener)
    }

    /**
     * 判断是否有监听器
     */
    fun hasOnViewChangedInRectListener(listener: OnViewChangedInRectListener): Boolean {
        return listener in onViewRectChangedListeners
    }

    /**
     * 开始进行事件监听
     */
    fun watch() {
        watchViewScrolling()
        isIntersected = view.getVisibleRect().intersect(targetRect)
        smartLogD {
            "view=$view;view.visibleRect=${view.getVisibleRect()};targetRect=$targetRect;isIntersected=$isIntersected"
        }
    }

    private fun onScrolling() {
        val currentViewRect = view.getVisibleRect()

        smartLogD {
            "onScrolling currentViewRect=$currentViewRect"
        }

        if (currentViewRect.intersect(targetRect)) {
            if(isIntersected) {
                smartLogD {
                    "onScrolling already intersected no need to notify"
                }
            } else {
                isIntersected = true
                onViewRectChangedListeners.forEach {
                    it.onViewAppearedInRect(view, targetRect)
                }
                smartLogD {
                    "onScrolling begin intersected and notify listeners"
                }
            }
        } else {
            if (isIntersected) {
                isIntersected = false
                onViewRectChangedListeners.forEach {
                    it.onViewDisappearedInRect(view, targetRect)
                }
                smartLogD {
                    "onScrolling begin not intersected and notify listeners"
                }
            } else {
                smartLogD {
                    "onScrolling already NOT intersected no need to notify"
                }
            }
        }
    }



    private fun watchViewScrolling() {
        view.viewTreeObserver.addOnScrollChangedListener {
            if (view.visibility == View.VISIBLE) {
                onScrolling()
            } else {
                smartLogD { "watchViewScrolling ignore due to view is not visible" }
            }
        }
    }
}