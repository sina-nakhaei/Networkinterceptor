package ir.thesinaa.networkinterceptor.performancemonitoringinterceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class PerformanceMonitoringInterceptor(
    private val logger: (String) -> Unit = { Log.d("PerformanceInterceptor", it) }
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val startTime = System.nanoTime()

        val response = try {
            chain.proceed(request)
        } catch (e: IOException) {
            logger("Request failed: ${e.message}")
            throw e
        }

        val endTime = System.nanoTime()

        val durationMs = TimeUnit.NANOSECONDS.toMillis(endTime - startTime)
        logRequestPerformance(request, response, durationMs)

        return response
    }

    private fun logRequestPerformance(
        request: okhttp3.Request,
        response: Response,
        durationMs: Long
    ) {
        val statusCode = response.code
        val url = request.url
        val method = request.method
        val responseSize = response.body?.contentLength() ?: 0
        val requestSize = request.body?.contentLength() ?: 0

        val logMessage = """
            Request URL: $url
            Method: $method
            Status Code: $statusCode
            Request Size: $requestSize bytes
            Response Size: $responseSize bytes
            Duration: $durationMs ms
        """.trimIndent()

        logger(logMessage)
    }
}