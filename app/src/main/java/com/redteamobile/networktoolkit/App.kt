package com.redteamobile.networktoolkit

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.redteamobile.networktoolkit.util.Logger
import io.reactivex.plugins.RxJavaPlugins

/**
 * @author SScience
 * @description
 * @email chentushen.science@gmail.com
 * @data 2019-08-29
 */
class App : Application() {

    companion object {

        const val TAG = "Network Toolkit"

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Context

    }

    override fun onCreate() {
        super.onCreate()

        instance = applicationContext

        // 无网情况下刷新页面因为 Observer 被 dispose 异常未能及时捕获导致页面崩溃
        RxJavaPlugins.setErrorHandler {
            it?.run {
                Logger.e(TAG, "uncaught error")
                this.printStackTrace()
            }
        }
    }
}
