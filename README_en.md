Coobox
===========
[![Release](https://jitpack.io/v/secoo-android/coobox.svg)](https://jitpack.io/#secoo-android/coobox)

  * Coobox is a powerful tool box that could speed-up your Android code developmenet.

Get Started
--------------
1. add the repository configuration in your root `build.gradle` file
```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

2. add dependency implementation in your module `build.gradle` file
```groovy
dependencies {
    implementation 'com.github.secoo-android:coobox:x.y.z'
}
```

Note: Please replace `x.y.z` with the latest version code.


Some API usage
--------------

### Do something when an activity destroys.
```kotlin
activity.doOnDestroy {
	XX.unregisterListener(this)
}
```
----

### Get a value safely or Run some execution safely.
```kotlin
val value = getValueSafely {
	// some code where exception may occurs
} ?: "fallback_value"


runSafely {
	// some code where exception may occurs
}
```

----

### APIs about String
```kotlin
// Change the string into a valid Uri if success, otherwise return null.

"https://m.secoo.com".toValidUri()

// Check whether a string is valid Uri or not
"Are you OK?".isValidUriString() //false 

```

----

### APIs about View
```kotlin
// make a view visible
aView.makeVisible()

// make a view invisible
aView.makeInvisible()

// make a view gone
aView.makeGone()

// pretend to click a view
aView.simulateClick()

// remove itself from a parent container
aView.removeSelf()
```

Full API Reference Link
=======
[Reference Link](https://secoo-android.github.io/coobox/library/)


License
=======

    Copyright 2021 The Secoo Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.