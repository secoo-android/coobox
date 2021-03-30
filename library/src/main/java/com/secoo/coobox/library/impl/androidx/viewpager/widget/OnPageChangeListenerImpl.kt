package com.secoo.coobox.library.impl.androidx.viewpager.widget

import androidx.viewpager.widget.ViewPager

/**
 * OnPageChangeListener 空实现
 */
open class OnPageChangeListenerImpl : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

}