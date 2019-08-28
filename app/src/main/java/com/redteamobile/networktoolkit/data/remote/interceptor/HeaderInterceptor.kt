package com.redteamobile.networktoolkit.data.remote.interceptor

import android.content.Context
import com.google.common.hash.Hashing
import com.redteamobile.networktoolkit.data.local.LocalCache
import com.redteamobile.networktoolkit.data.remote.service.HttpClient
import com.redteamobile.networktoolkit.util.Logger
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.util.*

class HeaderInterceptor(var context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .header("Accept-Language", localeForRequest())
//            .header("Charset", Charsets.UTF_8.name())
            .header("Content-Type", "application/json")
            .header("signature", signatureWithBody(request.body()))
        if (request.header("Authorization") != null) {
            // 需要使用token的，给api相应接口的@Headers("")增加"token: "即可
            val token = LocalCache.token(context)
            if (token.isNullOrEmpty()) {
                builder.removeHeader("Authorization")
            } else {
                builder.header("Authorization", token.toString())
            }
        }
        return chain.proceed(builder.build())
    }

    private fun localeForRequest(): String {
        return Locale.getDefault().toString()
    }

    private fun signatureWithBody(body: RequestBody?): String {
        return Hashing.sha1().newHasher().putString(body2String(body), Charsets.UTF_8).hash().toString()
    }

    private fun body2String(requestBody: RequestBody?): String {
        var bodyStr = ""
        if (requestBody != null) {
            val buffer = Buffer()
            try {
                requestBody.writeTo(buffer)
            } catch (e: IOException) {
                Logger.e(HttpClient.TAG, "body2String error: ", e)
            }
            bodyStr = buffer.readString(Charsets.UTF_8)
            if (!bodyStr.isEmpty()) {
                Logger.d(HttpClient.TAG, String.format("--> Body: %s", bodyStr))
            }
        }
        return bodyStr
    }

}