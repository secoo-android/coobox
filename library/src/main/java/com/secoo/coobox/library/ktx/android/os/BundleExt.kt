package com.secoo.coobox.library.ktx.android.os

import android.os.*
import android.util.Size
import android.util.SizeF
import java.io.Serializable

/**
 * 使用Key/Value 创建一个Bundle
 */
inline fun kvBundleOf(key: String?, value: Any?): Bundle {
    return Bundle().plus(key, value)
}

/**
 * 为现有的Bundle实例追加key，value
 */
fun  Bundle.plus(key: String?, value: Any?): Bundle {
    when (value) {
        null -> putString(key, null) // Any nullable type will suffice.

        // Scalars
        is Boolean -> putBoolean(key, value)
        is Byte -> putByte(key, value)
        is Char -> putChar(key, value)
        is Double -> putDouble(key, value)
        is Float -> putFloat(key, value)
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is Short -> putShort(key, value)

        // References
        is Bundle -> putBundle(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Parcelable -> putParcelable(key, value)

        // Scalar arrays
        is BooleanArray -> putBooleanArray(key, value)
        is ByteArray -> putByteArray(key, value)
        is CharArray -> putCharArray(key, value)
        is DoubleArray -> putDoubleArray(key, value)
        is FloatArray -> putFloatArray(key, value)
        is IntArray -> putIntArray(key, value)
        is LongArray -> putLongArray(key, value)
        is ShortArray -> putShortArray(key, value)

        // Reference arrays
        is Array<*> -> {
            val componentType = value::class.java.componentType!!
            @Suppress("UNCHECKED_CAST") // Checked by reflection.
            when {
                Parcelable::class.java.isAssignableFrom(componentType) -> {
                    putParcelableArray(key, value as Array<Parcelable>)
                }
                String::class.java.isAssignableFrom(componentType) -> {
                    putStringArray(key, value as Array<String>)
                }
                CharSequence::class.java.isAssignableFrom(componentType) -> {
                    putCharSequenceArray(key, value as Array<CharSequence>)
                }
                Serializable::class.java.isAssignableFrom(componentType) -> {
                    putSerializable(key, value)
                }
                else -> {
                    val valueType = componentType.canonicalName
                    throw IllegalArgumentException(
                        "Illegal value array type $valueType for key \"$key\"")
                }
            }
        }

        // Last resort. Also we must check this after Array<*> as all arrays are serializable.
        is Serializable -> putSerializable(key, value)

        else -> {
            if (Build.VERSION.SDK_INT >= 18 && value is IBinder) {
                putBinder(key, value)
            } else if (Build.VERSION.SDK_INT >= 21 && value is Size) {
                putSize(key, value)
            } else if (Build.VERSION.SDK_INT >= 21 && value is SizeF) {
                putSizeF(key, value)
            } else {
                val valueType = value.javaClass.canonicalName
                throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
            }
        }
    }
    return this
}


/**
 * 获取 Bundle 对象 对应的byte数量，用来确定 Bundle 在序列化中的大小。
 */
fun Bundle.byteCount(): Int {
    val parcel = Parcel.obtain().also {
        it.writeValue(this)
    }

    val bytes = parcel.marshall().also {
        parcel.recycle()
    }

    return bytes?.size ?: 0
}

