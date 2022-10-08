package com.sun.sunconcurrency

import android.os.Handler
import android.os.Looper
import android.util.Log

/**
 * @author hengyangji
 * on 2022/10/7
 */
object SunThread {
    val handler = Handler(Looper.getMainLooper())
    val sunLock = Object()
    fun testThread() {
        val thread = Thread {
            Thread.sleep(3000)
            Log.i("SunThread", "run, name:${Thread.currentThread().name}, state:${Thread.currentThread().state}")
            synchronized(sunLock) {
                sunLock.wait()
            }
            Log.i("SunThread", "after wait")
        }
//        thread.setDaemon(true)
        Log.i("SunThread", "after new, state:${thread.state}")
        thread.start()
        Log.i("SunThread", "after start, state:${thread.state}")
        handler.postDelayed(Runnable {
            Log.i("SunThread", "after sleep, state:${thread.state}")
        }, 1000)

        handler.postDelayed(Runnable {
            Log.i("SunThread", "after wait, state:${thread.state}")
            synchronized(sunLock) {
                sunLock.notify()
            }
        }, 4000)
    }
}