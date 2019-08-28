package com.redteamobile.networktoolkit.util

import android.util.Log

/**
 * log 控制
 * TODO: 分级打印 log
 * TODO: 按需求保存 log 到本地
 * TODO: log 上传
 */
object Logger {

    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    fun i(tag: String, msg: String, tr: Throwable) {
        Log.i(tag, msg, tr)
    }

    fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun d(tag: String, msg: String, tr: Throwable) {
        Log.d(tag, msg, tr)
    }

    fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }

    fun w(tag: String, tr: Throwable) {
        Log.w(tag, tr)
    }

    fun w(tag: String, msg: String, tr: Throwable) {
        Log.w(tag, msg, tr)
    }

    fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    fun e(tag: String, tr: Throwable) {
        Log.e(tag, "", tr)
    }

    fun e(tag: String, msg: String, tr: Throwable) {
        Log.e(tag, msg, tr)
    }

}