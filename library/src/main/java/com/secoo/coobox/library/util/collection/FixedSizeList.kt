package com.secoo.coobox.library.util.collection

import java.util.*

/**
 * 一个固定内容大小的容器，设定固定大小的item数量，当超过去除旧的item
 * 新的item位于最开始的位置，旧的位于后面的位置
 * 该容器只支持写入，不支持外部显式删除操作
 */
class FixedSizeList<T>(private val maxItemCount: Int = 5) {
    private val backingCollection = LinkedList<T>()

    /**
     * 添加元素
     */
    fun add(item: T?) {
        try {
            if (backingCollection.size >= maxItemCount) {
                backingCollection.removeLast()
            }
            backingCollection.addFirst(item)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun toString(): String {
        return "SizedList(maxItemCount=$maxItemCount, backingCollection=$backingCollection)"
    }

    /**
     * 获取全部元素
     */
    fun getItems(): List<T?> = backingCollection.toList()
}