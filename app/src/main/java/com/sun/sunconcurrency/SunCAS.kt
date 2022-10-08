package com.sun.sunconcurrency

import java.util.concurrent.atomic.AtomicInteger

/**
 * @author hengyangji
 * on 2022/10/8
 */
object SunCAS {
    fun test() {
        val addInt = AtomicInteger()
        addInt.get()
        addInt.getAndIncrement()
    }
}