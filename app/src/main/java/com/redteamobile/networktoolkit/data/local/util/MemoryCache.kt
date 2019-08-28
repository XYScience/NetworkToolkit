package com.redteamobile.networktoolkit.data.local.util

import android.util.LruCache

class MemoryCache : DataCache {

    private val mLruCache: LruCache<String, String>

    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        mLruCache = object : LruCache<String, String>(cacheSize) {
            override fun sizeOf(key: String, value: String): Int {
                return value.toByteArray().size
            }
        }
    }

    override fun get(key: String): String? {
        return mLruCache.get(key)
    }

    override fun put(key: String, value: String) {
        mLruCache.put(key, value)
    }

    override fun remove(key: String) {
        mLruCache.remove(key)
    }

}
