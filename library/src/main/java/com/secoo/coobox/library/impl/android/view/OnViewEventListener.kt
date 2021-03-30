package com.secoo.coobox.library.impl.android.view

import android.os.Bundle
import android.view.View

interface OnViewEventListener {
    /**
     * 视图点击回调
     */
    fun afterViewClicked(view: View, key: String?, data: Any?, extra: Bundle?)

    /**
     * 视图展示的回调，多用于处理埋点向上层抛出
     */
    fun onViewShown(view: View, key: String?, data: Any?, extra: Bundle?)
}