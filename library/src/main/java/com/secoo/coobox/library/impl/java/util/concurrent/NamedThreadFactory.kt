package com.secoo.coobox.library.impl.java.util.concurrent

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

/**
 * 一个有名称的 ThreadFactory，用来实现做一些线程创建分析
 */
class NamedThreadFactory : ThreadFactory {
    private var group: ThreadGroup? = null
    private val threadNumber = AtomicInteger(1)
    private var namePrefix: String? = null

    private constructor() {}

    constructor(name: String?) {
        group = System.getSecurityManager()?.threadGroup ?: Thread.currentThread().threadGroup
        namePrefix = "($name)pool-" + poolNumber.getAndIncrement() + "-thread-"
    }

    override fun newThread(r: Runnable): Thread {
        val t = Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0)

        if (t.isDaemon) {
            t.isDaemon = false
        }

        if (t.priority != Thread.NORM_PRIORITY) {
            t.priority = Thread.NORM_PRIORITY
        }
        return t
    }

    companion object {
        private val poolNumber = AtomicInteger(1)
    }
}