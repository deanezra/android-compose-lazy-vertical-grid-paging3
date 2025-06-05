package com.deanezra.chicagoart.data.network

import com.deanezra.chicagoart.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/* Web API requires we project a custom user agent string with project name and email for tracking
 * This interceptor pulls the email out of BuildConfig (which gets it from your environment variable
 * and then sets the user agent on every retrofit call
 */
class UserAgentInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val email = BuildConfig.CONTACT_EMAIL_FOR_USER_AGENT ?: ""
        val original: Request = chain.request()
        val httpUrl: HttpUrl = original.url
        val url: HttpUrl = httpUrl.newBuilder()
            .addQueryParameter("AIC-User-Agent", "art-viewer (${email})")
            .build()
        val request: Request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}