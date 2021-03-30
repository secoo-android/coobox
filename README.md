Coobox
===========
[![Release](https://jitpack.io/v/secoo-android/coobox.svg)](https://jitpack.io/#secoo-android/coobox)


  * Coobox 是一个加速 Android 应用开发编码效率的工具箱
  * 名字来源: Secoo + Box Coo取自Secoo，Box则有两层意思，其一是工具箱，其二则是寺库 Logo 礼物盒子

开始使用
--------------
1. 在项目根目录下的 `build.gradle` 增加仓库配置 
```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

2. 在模块下的 `build.gradle` 增加依赖引用 
```groovy
dependencies {
    implementation 'com.github.secoo-android:coobox:x.y.z'
}
```

注: 请手动替换 x.y.z 为最新的版本信息


部分示例
--------------

### 在 Activity onDestroy 时执行
```kotlin
activity.doOnDestroy {
	XX.unregisterListener(this)
}
```
----

### 安全地获取值/执行
```kotlin
val value = getValueSafely {
	// 一些代码，可能出现异常
} ?: "fallback_value"


runSafely {
	// 一些代码，可能出现异常
}
```

----

### 字符串操作
```kotlin
// 将字符串转成合格的 Uri，否则为 null

"https://m.secoo.com".toValidUri()

// 检测某个字符串是否是合格的 Uri
"Are you OK?".isValidUriString() //false 

```

----

### 视图操作
```kotlin
// 设置视图可见
aView.makeVisible()

// 设置视图不可见
aView.makeInvisible()

// 设置视图消失
aView.makeGone()

// 模拟点击视图
aView.simulateClick()

// 将视图从父容器移除
aView.removeSelf()
```

完整文档
=======
[API 具体文档](https://secoo-android.github.io/coobox/library/)


License
=======

    Copyright 2021 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.