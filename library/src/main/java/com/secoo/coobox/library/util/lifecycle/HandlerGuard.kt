package com.secoo.coobox.library.util.lifecycle

import android.app.Activity
import android.os.Handler
import androidx.fragment.app.Fragment
import com.secoo.coobox.library.ktx.android.app.doOnDestroy
import com.secoo.coobox.library.ktx.android.os.mainThreadHandler
import com.secoo.coobox.library.ktx.android.os.toDescription
import com.secoo.coobox.library.ktx.androidx.fragment.app.doOnDestroy
import com.secoo.coobox.library.logger.smartLogD

/**
 * 处理Handler与生命周期载体的解绑关系
 */
object HandlerGuard {

    /**
     * 监控并处理handler的runnable
     * 当activity销毁的时候，从handler中移除runnable待执行任务
     */
    fun watch(activity: Activity?, runnable: Runnable?, handler: Handler? = mainThreadHandler) {
        smartLogD {
            "watch activity=$activity;handler=$handler;runnable=$runnable"
        }

        activity ?: return
        runnable ?: return
        val finalHandler = handler ?: mainThreadHandler

        activity.doOnDestroy {
            finalHandler.removeCallbacks(runnable)
            smartLogD {
                "watch activity($activity).onDestroy remove $runnable from ${finalHandler.toDescription()}"
            }
        }

    }

    /**
     * 监控并处理 handler 的 runnable
     * 当 Fragment 销毁的时候，从 handler 中移除 runnable 待执行任务
     */
    fun watch(fragment: Fragment?, runnable: Runnable?, handler: Handler? = mainThreadHandler) {
        smartLogD {
            "watch fragment=$fragment;runnable=$runnable;handler=$handler"
        }

        fragment ?: return
        runnable ?: return
        val finalHandler = handler ?: mainThreadHandler

        fragment.doOnDestroy {
            finalHandler.removeCallbacks(runnable)
            smartLogD {
                "watch fragment($fragment).onDestroy remove $finalHandler from ${finalHandler.toDescription()}"
            }
        }
    }
}