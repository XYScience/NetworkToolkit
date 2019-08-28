package com.redteamobile.networktoolkit.data.remote.service.api

import com.redteamobile.networktoolkit.data.remote.service.model.request.RegisterRequest
import com.redteamobile.networktoolkit.data.remote.service.model.response.BasicResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkToolkitApi {

    object BaseUrl {
        const val PROD = "https://xxx/"
        const val STAGING = "https://xxx/"
        const val QA = "http://xxx/"
    }

    /**
     * 注册账号
     */
    @POST("xxx/user/register")
    fun register(@Body request: RegisterRequest): Observable<BasicResponse>

}
