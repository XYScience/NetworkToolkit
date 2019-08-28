package com.redteamobile.networktoolkit.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build

@Suppress("DEPRECATION")
object SPUtil {

    val PREF_NAME = "preferences"
    val NET_LOG = "net_log"

    @SuppressLint("InlinedApi")
    fun getPrefs(context: Context): SharedPreferences {
        var code = Context.MODE_MULTI_PROCESS
        code = code or Context.MODE_APPEND
        return context.getSharedPreferences(PREF_NAME, code)
    }


    fun saveNetLog(context: Context, value: Boolean) {
        getPrefs(context).edit().putBoolean(NET_LOG, value).apply()
    }

    fun hasNetLog(context: Context): Boolean {
        return getPrefs(context).getBoolean(NET_LOG, false)
    }
}
