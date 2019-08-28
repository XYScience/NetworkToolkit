package com.redteamobile.networktoolkit.data.remote.service.util

import android.content.Context
import android.net.ConnectivityManager
import com.redteamobile.networktoolkit.data.remote.service.model.response.BasicResponse
import com.redteamobile.networktoolkit.util.Logger
import retrofit2.Response

object NetworkUtil {

    private const val TAG = "NetworkUtil"

    fun isOnline(context: Context): Boolean {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        if (networkInfo == null) {
            Logger.i(TAG, "networkInfo is null")
            return false
        }
        val state = networkInfo.state
        if (state == null) {
            Logger.i(TAG, "networkInfo state is null")
            return false
        }
        Logger.i(TAG, String.format("networkInfo state is %s", state.toString()))
        return networkInfo.isConnected
    }

    fun isWifiConn(context: Context): Boolean {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return networkInfo != null && networkInfo.isConnected
    }

    fun isMobileConn(context: Context): Boolean {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return networkInfo != null && networkInfo.isConnected
    }

    fun checkResponseSuccess(response: Response<out BasicResponse>): Boolean {
        return response.isSuccessful && response.body()!!.success
    }

}
