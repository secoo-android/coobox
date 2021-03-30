package com.secoo.coobox.library.ktx.java.lang

import java.lang.reflect.Modifier

/**
 * 获取类的便于展示的名称
 * 如果时匿名内部类，则需要去除对应的包名信息
 */
val Class<*>.displayName: String
    get() {
        return if (isAnonymousClass) {
            name.replace(this.`package`?.name ?: "FAKE_PACKAGE_HOLDER", "")
        } else {
            simpleName
        }
    }

/**
 * Check the class is abstract or not
 */
fun Class<*>.isAbstract(): Boolean {
    return Modifier.isAbstract(this.modifiers)
}

/**
 * Check the class is not abstract
 */
fun Class<*>.isNotAbstract(): Boolean {
    return !isAbstract()
}