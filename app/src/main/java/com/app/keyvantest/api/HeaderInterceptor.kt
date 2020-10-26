package com.app.keyvantest.api

import android.os.Build
import com.app.keyvantest.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val userAgent =
            "Android ${Build.MANUFACTURER} ${Build.BRAND} ${Build.MODEL} ${Build.VERSION.SDK_INT}"
        val request = chain.request().newBuilder()
            /**
             * SomeTimes No Needed Bearer Word in Authorization
             */
            .addHeader("Authorization", "Bearer ${Constants.ACCESS_TOKEN}")
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", userAgent)
            .build()
        return chain.proceed(request)
    }
}