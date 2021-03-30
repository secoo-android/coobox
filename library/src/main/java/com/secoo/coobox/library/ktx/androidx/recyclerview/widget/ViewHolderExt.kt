package com.secoo.coobox.library.ktx.androidx.recyclerview.widget

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.secoo.coobox.library.ktx.android.view.hostActivity
import com.secoo.coobox.library.ktx.android.view.hostFragmentActivity

/**
 * 获取当前ViewHolder所在的Activity
 */
val RecyclerView.ViewHolder.hostActivity: Activity?
    get() = itemView.hostActivity

/**
 * 获取当前ViewHolder所在的FragmentActivity
 */
val RecyclerView.ViewHolder.hostFragmentActivity: FragmentActivity?
    get() = itemView.hostFragmentActivity

