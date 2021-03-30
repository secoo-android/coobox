package com.secoo.coobox.library.ktx.java.util

import java.util.*

/**
 * 创建一个stack示例
 */
fun <E> stackOf() = Stack<E>()

/**
 * 安全地执行pop方法，如果stack为空，返回null
 */
fun <E> Stack<E>.safePop(): E? {
    return if (this.isEmpty()) {
        null
    } else {
        pop()
    }
}

/**
 * 安全地执行peek方法，如果stack为空，返回null
 */
fun <E> Stack<E>.safePeek(): E? {
    return if (this.isEmpty()) {
        null
    } else {
        peek()
    }
}