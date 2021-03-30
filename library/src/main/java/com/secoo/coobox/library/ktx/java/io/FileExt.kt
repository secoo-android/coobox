package com.secoo.coobox.library.ktx.java.io

import com.secoo.coobox.library.util.crash.getValueSafely
import org.apache.commons.io.FileUtils
import java.io.File

/**
 *  获取目录的文件大小，bytes为单位
 *  如果是非目录，返回null
 */
val File.directorySize: Long?
    get() {
        return getValueSafely {
            FileUtils.sizeOfDirectory(this)
        }
    }

