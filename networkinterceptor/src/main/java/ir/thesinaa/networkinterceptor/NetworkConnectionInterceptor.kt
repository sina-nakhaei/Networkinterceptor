package ir.thesinaa.networkinterceptor

import android.content.Context
import ir.thesinaa.networkinterceptor.util.ConnectivityUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!ConnectivityUtil.isOnline(context)) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}