package com.secoo.coobox.library.ktx.kotlin

/**
 * 如果item不是null，添加到列表中
 */
fun <T> MutableList<T>.addIfNotNull(item: T?) {
    item?.let(this::add)
}

/**
 * 如果item不是null，从列表中删除
 */
fun <T> MutableList<T>.removeIfNotNull(item: T?) {
    item?.let(this::remove)
}