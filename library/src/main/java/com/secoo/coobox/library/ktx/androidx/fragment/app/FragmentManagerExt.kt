package com.secoo.coobox.library.ktx.androidx.fragment.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * 检测 Fragment 是否添加到 FragmentManager 之中
 */
fun FragmentManager.isFragmentAdded(fragment: Fragment): Boolean {
    return fragment in fragments
}

/**
 * 检测 Fragment 没有添加到 FragmentManager 之中
 */
fun FragmentManager.isFragmentNotAdded(fragment: Fragment): Boolean {
    return !isFragmentAdded(fragment)
}