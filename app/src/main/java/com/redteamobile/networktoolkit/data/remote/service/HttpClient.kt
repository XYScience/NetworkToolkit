package com.redteamobile.networktoolkit.data.remote.service

import android.content.Context
import com.redteamobile.networktoolkit.BuildConfig
import com.redteamobile.networktoolkit.data.remote.interceptor.HeaderInterceptor
import com.redteamobile.networktoolkit.util.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object HttpClient {

    const val TAG = "HttpClient"

    private object Configuration {
        const val CONNECT_TIMEOUT: Long = 10
        const val WRITE_TIMEOUT: Long = 10
        const val READ_TIMEOUT: Long = 30
    }

    fun create(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Configuration.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Configuration.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Configuration.READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor { message ->
                Logger.d(
                    TAG,
                    message
                )
            }.setLevel(levelOfLog()))
            .build()
    }

    fun environment(): Int {
        return BuildConfig.environment
    }

    private fun levelOfLog(): HttpLoggingInterceptor.Level {
        return HttpLoggingInterceptor.Level.BODY
    }

}
