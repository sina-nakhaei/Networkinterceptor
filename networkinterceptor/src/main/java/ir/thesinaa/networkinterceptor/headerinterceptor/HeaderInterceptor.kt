package ir.thesinaa.networkinterceptor.headerinterceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val headersProvider: () -> Map<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        headersProvider().forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }

        return chain.proceed(requestBuilder.build())
    }
}