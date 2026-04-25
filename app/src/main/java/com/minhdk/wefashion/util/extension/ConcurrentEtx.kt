package com.minhdk.wefashion.util.extension

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

suspend fun <T> Mutex.executeWithLock(lock: Any? = null, action: suspend () -> T?): T? {
    return lock?.let {
        withLock(lock) {
            action()
        }
    } ?: run {
        withLock {
            action()
        }
    }
}