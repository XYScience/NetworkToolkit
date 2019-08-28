package com.redteamobile.networktoolkit.data.local

import android.content.Context
import com.redteamobile.networktoolkit.data.local.util.CacheManager
import com.redteamobile.networktoolkit.data.remote.service.util.fromJson
import com.redteamobile.networktoolkit.data.remote.service.util.toJson

/**
 * @author SScience
 * @description
 * @email chentushen.science@gmail.com
 * @data 2019-08-29
 */
object LocalCache {

    private const val KEY_TOKEN = "token"

    fun token(context: Context): String? {
        return load(context, KEY_TOKEN)
    }

    fun saveToken(context: Context, text: String?) {
        save(context, KEY_TOKEN, text)
    }

    private fun save(context: Context, key: String, value: Any?) {
        val valueStr = value?.toJson()
        if (valueStr.isNullOrBlank()) {
            getCacheManager(context).remove(key)
        } else {
            getCacheManager(context).put(key, valueStr)
        }
    }

    private fun <T> loadBean(context: Context, key: String, clazz: Class<T>): T? {
        return load(context, key)?.fromJson(clazz)
    }

    private fun load(context: Context, key: String): String? {
        return getCacheManager(context)[key]
    }

    private fun getCacheManager(context: Context): CacheManager {
        return CacheManager.getInstance(context)
    }

}
