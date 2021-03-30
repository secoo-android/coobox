package com.secoo.coobox.library

/**
 *
 * 类名描述信息,测试文档生成类
 * @param T 泛型类型
 * @property value 属性value
 * @constructor 创建DokkaDocTest的构造器.
 * @Author haiyang
 *
 */
class DokkaDocTest<T>(val value: String) {

    /**
     * 属性arg
     */
    val arg1:Int =  0

    /**
     * Add 是一DokkaDocTest的一个方法
     * @param[a] a是方法的入参
     * @sample com.secoo.coobox.library.samples.TheSamples.helloWorldSample
     * @return 返回计算结果
     */
    fun add(a: Int):Int {

        return a
    }


    /**
     * subtract 是一DokkaDocTest的一个方法
     * @param b b是方法的入参
     * @return 返回计算结果
     */
    fun subtract(b:Int):Int {
        return b
    }


}