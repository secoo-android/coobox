package com.secoo.coobox.library.ktx.kotlin

/**
 * 更正index
 * 1.如果index合法，原样返回
 * 2.如果index不合法，返回@fallbackIndex
 */
@JvmOverloads
fun <E> List<E>.correctIndex(index: Int, fallbackIndex: Int = 0): Int {
    return if (isIndexOutOfBounds(index)) {
        fallbackIndex
    } else {
        index
    }
}

/**
 * index 是否越界
 */
fun <E> List<E>.isIndexOutOfBounds(index: Int): Boolean {
    return !isIndexValid(index)
}

/**
 * index是否合法（不越界）
 */
fun <E> List<E>.isIndexValid(index: Int): Boolean {
    return index >= 0 && index <= this.lastIndex
}

/**
 * List 不为空
 */
fun <E> List<E>.isNotEmpty(): Boolean {
    return this.size > 0
}

/**
 * 获取第二个元素,如果List小于2个元素，返回null
 */
fun <E> List<E>.secondOrNull(): E? {
    return this.getOrNull(1)
}

/**
 * 获取倒数第二个元素,如果List小于2个元素，返回null
 */
fun <E> List<E>.secondLastOrNull(): E? {
    return this.getOrNull(this.lastIndex - 1)
}

/**
 * 实现 toString 方法进行更好的输出内容
 */
fun <E> List<E>?.toString(): String? {
    return this?.joinToString {
        it.toString()
    }
}

/**
 * 判断 List 对象 只包含一个元素
 */
fun <E> List<E>?.onlyOneItem(): Boolean {
    return this?.size == 1
}

/**
 * 判断 List 对象不包含某个元素
 */
fun <E> List<E>?.notContains(item: E): Boolean {
    return this?.contains(item) != true
}

/**
 * 将 当前 List 对象转换成 ArrayList
 */
fun <E> List<E>.toArrayList(): ArrayList<E> {
    return ArrayList(this)
}
