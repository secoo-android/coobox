package com.secoo.coobox.library.ktx.android.view

import android.app.Activity
import android.graphics.Rect
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.secoo.coobox.library.alias.type.KRunnable
import com.secoo.coobox.library.alias.type.ViewToUnit
import com.secoo.coobox.library.ktx.android.app.asFragmentActivity
import com.secoo.coobox.library.ktx.android.content.activity
import com.secoo.coobox.library.ktx.kotlin.asType
import com.secoo.coobox.library.util.crash.runSafely

/**
 * 在视图从 Window 分离时 执行操作
 */
fun View?.doOnDetachedFromWindow(runnable: KRunnable?) {
    this ?: return
    runnable ?: return

    this.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View?) {
            runnable.invoke()
            v?.removeOnAttachStateChangeListener(this)
        }

        override fun onViewAttachedToWindow(v: View?) {
        }

    })
}

/**
 * 在视图添加到 Window 时， 执行操作
 */
fun View?.doOnViewAttachedToWindow(runnable: KRunnable?) {
    this ?: return
    runnable ?: return

    this.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View?) {
            v?.removeOnAttachStateChangeListener(this)
        }

        override fun onViewAttachedToWindow(v: View?) {
            runnable.invoke()
        }

    })
}

/**
 * 获取视图在全局的可见矩形
 */
fun View.getVisibleRect(): Rect {
    val rect = Rect()
    getGlobalVisibleRect(rect)
    return rect
}

/**
 * 在视图被点击时 执行操作
 */
fun View?.doOnClick(onClickAction: ViewToUnit?) {
    this?.setOnClickListener {
        onClickAction?.invoke(it)
    }
}

/**
 * 获取当前view所在的activity
 */
val View.hostActivity: Activity?
    get() = this.context.activity()

/**
 * 获取当前View所在的FragmentActivity
 */
val View.hostFragmentActivity: FragmentActivity?
    get() = hostActivity?.asFragmentActivity()

/**
 * 视图变为可见
 */
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

/**
 * 视图变为gone
 */
fun View.makeGone() {
    this.visibility = View.GONE
}

/**
 * 视图变为不可见
 */
fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

/**
 * 将自己从父容器中移除
 */
fun View?.removeSelf() {
    this ?: return
    runSafely {
        this.parent?.asType<ViewGroup>()?.removeView(this)
    }
}

/**
 * 模拟视图的点击事件
 */
fun View?.simulateClick() {
    this?.simulateClick(0f, 0f)
}

/**
 * 模拟视图的点击事件（可以设置 x 和 y 坐标）
 */
fun View?.simulateClick(x: Float, y: Float) {
    this ?: return

    val downTime = SystemClock.uptimeMillis()
    val eventTime = SystemClock.uptimeMillis() + 100
    val metaState = 0
    val motionEvent = MotionEvent.obtain(downTime, eventTime,
        MotionEvent.ACTION_DOWN, x, y, metaState)

    this.dispatchTouchEvent(motionEvent)

    val upEvent = MotionEvent.obtain(downTime + 1000, eventTime + 1000,
        MotionEvent.ACTION_UP, x,y, metaState)
    this.dispatchTouchEvent(upEvent)
}