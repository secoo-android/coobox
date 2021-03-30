package com.secoo.coobox.library.ktx.android.view

import android.view.MotionEvent

fun MotionEvent?.isActionUp(): Boolean {
    return this?.action == MotionEvent.ACTION_UP
}

fun MotionEvent?.isActionDown(): Boolean {
    return this?.action == MotionEvent.ACTION_DOWN
}

fun MotionEvent?.isActionCancel(): Boolean {
    return this?.action == MotionEvent.ACTION_CANCEL
}