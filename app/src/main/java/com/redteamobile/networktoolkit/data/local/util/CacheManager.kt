package com.redteamobile.networktoolkit.data.local.util

import android.content.Context

//TODO fileCache 要放到子线程来做
class CacheManager private constructor(context: Context) : DataCache {

    private val memoryCache = MemoryCache()
    private val fileCache: FileCache =
        FileCache(context)

    override fun get(key: String): String? {
        var value: String? = memoryCache[key]
        if (value == null) {
            value = fileCache[key]
            if (value != null) {
                memoryCache.put(key, value)
            }
        }
        return value
    }

    override fun put(key: String, value: String) {
        fileCache.put(key, value)
        memoryCache.put(key, value)
    }

    override fun remove(key: String) {
        fileCache.remove(key)
        removeMemory(key)
    }

    private fun removeMemory(key: String) {
        memoryCache.remove(key)
    }

    companion object {

        private val TAG = "CacheManager"
        @Volatile
        private var cacheManager: CacheManager? = null

        fun getInstance(context: Context): CacheManager {
            if (cacheManager == null) {
                synchronized(CacheManager::class.java) {
                    if (cacheManager == null) {
                        cacheManager =
                            CacheManager(context)
                    }
                }
            }
            return cacheManager!!
        }
    }

}
