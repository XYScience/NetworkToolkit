package com.redteamobile.networktoolkit.data.remote.service

import android.content.Context
import com.google.gson.Gson
import com.redteamobile.networktoolkit.data.remote.service.api.NetworkToolkitApi
import com.redteamobile.networktoolkit.data.remote.service.model.request.RegisterRequest
import com.redteamobile.networktoolkit.data.remote.service.model.response.BasicResponse
import com.redteamobile.networktoolkit.data.remote.transformer.RequestTransformer
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkToolkitService(ctx: Context) {


    companion object {
        private const val TAG = "NetworkToolkitService"
    }

    private val gson = Gson()
    private val context = ctx.applicationContext

    private var redteaGOApi = Retrofit.Builder()
        .client(HttpClient.create(context))
        .baseUrl(baseUrl())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkToolkitApi::class.java)

    private fun baseUrl(): String {
        return when (HttpClient.environment()) {
            1 -> NetworkToolkitApi.BaseUrl.QA
            2 -> NetworkToolkitApi.BaseUrl.STAGING
            else -> NetworkToolkitApi.BaseUrl.PROD
        }
    }

    fun register(
        request: RegisterRequest
    ): Observable<BasicResponse> {
        return redteaGOApi.register(request)
            .compose(RequestTransformer<BasicResponse>())
    }

}
