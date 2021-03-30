package com.secoo.coobox.library.lifecycle

import android.app.Activity

interface OnAppStateChangedListener {
    fun onAppStateChanged(state: AppStateHelper.Message, activity: Activity)
}