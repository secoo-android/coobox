package com.secoo.coobox.library.alias.type

import android.app.Activity
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.Fragment

typealias KRunnable = () -> Unit

typealias FragmentToUnit = (Fragment) -> Unit

typealias NullableFragmentToUnit = (Fragment?) -> Unit

typealias ViewToUnit = (View) -> Unit

typealias WebViewToUnit = (WebView) -> Unit

typealias BooleanToUnit = (Boolean) -> Unit

typealias ActivityToUnit = (Activity) -> Unit

typealias StrStrMap = HashMap<String, String>

typealias NullableStrStrMap = HashMap<String?, String>

typealias StrNullableStrMap = HashMap<String, String?>

typealias NullableStrNullableStrMap = HashMap<String?, String?>

