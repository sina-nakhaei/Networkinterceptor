package ir.thesinaa.networkinterceptor.retryinterceptor


import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private class RetryInterceptor(private val config: Config) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response? = null
        var attempt = 0

        while (attempt < config.maxRetryAttempts) {
            try {
                response = chain.proceed(request)
                if (response.isSuccessful) return response
            } catch (e: IOException) {
                if (attempt >= config.maxRetryAttempts - 1) throw e
            }

            Thread.sleep(config.delayMillis)
            attempt++
        }

        return response ?: throw IOException("Failed after ${config.maxRetryAttempts} attempts")
    }
}