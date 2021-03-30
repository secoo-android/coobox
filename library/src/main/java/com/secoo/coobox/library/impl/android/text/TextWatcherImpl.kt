package com.secoo.coobox.library.impl.android.text

import android.text.Editable
import android.text.TextWatcher

/**
 * 一个简单的 TextWatcher实现，避免直接实现接口需要实现多个方法的麻烦。
 */
open class TextWatcherImpl : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}