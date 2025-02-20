package ir.thesinaa.networkinterceptor.httprequestinterceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        Log.d("network-interceptor", request.toString())
        return chain.proceed(request)
    }
}