package com.secoo.coobox.library.ktx.androidx.fragment.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * 获取当前FragmentActivity直接的fragments列表
 */
val FragmentActivity.fragments: List<Fragment>
    get() = supportFragmentManager?.fragments ?: listOf()


/**
 * 根据 Tag 查找 Fragment
 */
fun FragmentActivity.findFragmentByTag(tag: String?): Fragment? {
    return this.supportFragmentManager.findFragmentByTag(tag)
}

/**
 * 根据 Tag 查找是否有对应的 Fragment 存在
 */
fun FragmentActivity.hasFragment(tag: String?): Boolean {
    return findFragmentByTag(tag) != null
}