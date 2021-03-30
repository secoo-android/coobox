package com.secoo.coobox.library.ktx.androidx.fragment.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.secoo.coobox.library.alias.type.FragmentToUnit
import com.secoo.coobox.library.ktx.androidx.lifecycle.*
import com.secoo.coobox.library.util.crash.runSafely

/**
 * 在Fragment onStart 时执行一次 操作
 */
fun Fragment.doOnStartOnce(action: FragmentToUnit) {
    lifecycle.doOnStart(true) {
        action.invoke(this)
    }
}

/**
 * 在 Fragment onStart 时总是执行操作
 */
fun Fragment.doOnStartAlways(action: FragmentToUnit) {
    lifecycle.doOnStart(false) {
        action.invoke(this)
    }
}

/**
 * 在Fragment onResume 时执行一次操作
 */
fun Fragment.doOnResumeOnce(action: FragmentToUnit) {
    lifecycle.doOnResume(true) {
        action.invoke(this)
    }
}

/**
 * 在 Fragment onResume 时，总是执行操作
 */
fun Fragment.doOnResumeAlways(action: FragmentToUnit) {
    lifecycle.doOnResume(false) {
        action.invoke(this)
    }
}

/**
 * 在 Fragment onPause 时，执行一次操作
 */
fun Fragment.doOnPauseOnce(action: FragmentToUnit) {
    lifecycle.doOnPause(true) {
        action.invoke(this)
    }
}

/**
 * 在 Fragment onPause 时，总是执行操作
 */
fun Fragment.doOnPauseAlways(action: FragmentToUnit) {
    lifecycle.doOnPause(false) {
        action.invoke(this)
    }
}

/**
 * 在 Fragment onStop 时 执行一次操作
 */
fun Fragment.doOnStopOnce(action: FragmentToUnit) {
    lifecycle.doOnStop(true) {
        action.invoke(this)
    }
}

/**
 * 在 Fragment onStop 时，总是执行操作
 */
fun Fragment.doOnStopAlways(action: FragmentToUnit) {
    lifecycle.doOnStop(false) {
        action.invoke(this)
    }
}

/**
 * 在Fragment onDestroy时调用执行某些操作
 */
fun Fragment.doOnDestroy(action: FragmentToUnit) {
    lifecycle.doOnDestroy(true) {
        action.invoke(this)
    }
}


/**
 * 安全执行commitNowAllowingStateLoss
 */
fun Fragment.showNowAllowingStateLossSafely(fragmentManager: FragmentManager,
                                            tag: String = this::class.java.simpleName) {
    if (fragmentManager.isFragmentAdded(this)) {
        return
    }

    runSafely {
        fragmentManager.beginTransaction().add(this, tag).commitNowAllowingStateLoss()
    }
}