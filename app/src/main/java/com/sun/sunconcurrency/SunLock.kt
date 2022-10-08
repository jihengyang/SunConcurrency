package com.sun.sunconcurrency

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * @author hengyangji
 * on 2022/10/8
 */
object SunLock {
    fun test() {
        val lock = ReentrantLock()
        lock.lock()
        lock.unlock()
    }

    fun rwLock() {
        val readWriteLock = ReentrantReadWriteLock()
        val readLock = readWriteLock.readLock()
        val writeLock = readWriteLock.writeLock()

    }

    fun doRead(readLock:Lock) {
        try {
            readLock.lock()
            //doRead
        } finally {
            readLock.unlock()
        }
    }
    private val fileLockHM = HashMap<String, ReentrantReadWriteLock>()
    private fun getFileLock(path: String):ReentrantReadWriteLock {
        if (fileLockHM.containsKey(path)) {
            return fileLockHM[path]!!
        }
        return ReentrantReadWriteLock().also {
            fileLockHM[path] = it
        }
    }
}