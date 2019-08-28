package com.redteamobile.networktoolkit.data.remote.service.util

import com.google.gson.Gson
import com.redteamobile.networktoolkit.App
import com.redteamobile.networktoolkit.util.Logger
import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5(): String {
    if (this.isEmpty()) {
        return ""
    }
    val sb = StringBuilder()
    try {
        val md5 = MessageDigest.getInstance("MD5")
        val byteData = md5.digest(this.toByteArray())
        byteData.forEach {
            var temp = Integer.toHexString(it.toInt() and 0xff)
            if (temp.length == 1) {
                temp = "0$temp"
            }
            sb.append(temp)
        }
    } catch (e: NoSuchAlgorithmException) {
    }
    return sb.toString()
}

fun <T> String?.fromJson(t: Class<T>): T? {
    return try {
        Gson().fromJson(this, t)
    } catch (e: Exception) {
        Logger.e(App.TAG, "fromJson error: ", e)
        null
    }
}

fun Any?.toJson(): String? {
    return if (this == null) {
        null
    } else {
        try {
            Gson().toJson(this)
        } catch (e: Exception) {
            Logger.e(App.TAG, "toJson error: ", e)
            null
        }
    }
}
