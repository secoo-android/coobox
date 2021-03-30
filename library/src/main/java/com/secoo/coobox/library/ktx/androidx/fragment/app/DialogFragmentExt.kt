package com.secoo.coobox.library.ktx.androidx.fragment.app

import androidx.fragment.app.DialogFragment
import com.secoo.coobox.library.util.crash.runSafely

/**
 * 安全地执行dismiss
 */
fun DialogFragment.dismissSafely() {
    runSafely {
        this.dismiss()
    }
}