package com.redteamobile.networktoolkit.data.remote

import android.content.Context
import com.redteamobile.networktoolkit.data.remote.service.NetworkToolkitService

/**
 * @author SScience
 * @description
 * @email chentushen.science@gmail.com
 * @data 2019-08-29
 */
class HttpManager private constructor(context: Context){

    companion object {
        private const val TAG = "HttpManager"

        @Volatile
        private var instance: HttpManager? = null

        fun getInstance(context: Context): HttpManager {
            if (instance == null) {
                synchronized(HttpManager::class.java) {
                    if (instance == null) {
                        instance = HttpManager(context)
                    }
                }
            }
            return instance!!
        }
    }

    var networkToolkitService = NetworkToolkitService(context)
}
