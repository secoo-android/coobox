package com.secoo.coobox.library.impl.androidx.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView
import com.secoo.coobox.library.util.crash.getValueSafely

/**
 * 包裹NestedScrollView 处理一些框架的一些异常情况
 */
class SafeNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return getValueSafely {
            super.onTouchEvent(ev)
        } ?: false
    }
}