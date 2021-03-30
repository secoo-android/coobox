package com.secoo.coobox.library.ktx.android.webkit

import android.webkit.WebView
import com.secoo.coobox.library.util.crash.runSafely

/**
 * WebView 开启硬件加速
 */
fun WebView?.enableHardwareAcceleration() {
    runSafely {
        this?.setLayerType(WebView.LAYER_TYPE_HARDWARE, null)
    }
}

/**
 * WebView 关闭硬件加速
 */
fun WebView?.disableHardwareAcceleration() {
    runSafely {
        this?.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)
    }
}