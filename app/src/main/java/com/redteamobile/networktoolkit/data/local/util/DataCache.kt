package com.redteamobile.networktoolkit.data.local.util

interface DataCache {

    operator fun get(key: String): String?

    fun put(key: String, value: String)

    fun remove(key: String)

}
