package ir.thesinaa.networkinterceptor.compressorinterceptor

import ir.thesinaa.networkinterceptor.compressorinterceptor.compressor.Compressor
import ir.thesinaa.networkinterceptor.compressorinterceptor.compressor.GzipCompressor
import okhttp3.*
import okio.Buffer

class CompressionInterceptor(
    private val compressor: Compressor = GzipCompressor(),
    private val compressThreshold: Long = 1024
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val compressedRequest = if (originalRequest.body != null) {
            compressRequest(originalRequest)
        } else {
            originalRequest
        }

        val response = chain.proceed(compressedRequest)

        return decompressResponse(response)
    }

    private fun compressRequest(request: Request): Request {
        val originalBody = request.body ?: return request
        val buffer = Buffer()
        originalBody.writeTo(buffer)
        val requestBytes = buffer.readByteArray()

        return if (requestBytes.size > compressThreshold) {
            val compressedBytes = compressor.compress(requestBytes)
            val compressedBody = RequestBody.create(originalBody.contentType(), compressedBytes)

            request.newBuilder()
                .header("Content-Encoding", compressor.encodingName())
                .method(request.method, compressedBody)
                .build()
        } else {
            request
        }
    }

    private fun decompressResponse(response: Response): Response {
        val encoding = response.header("Content-Encoding")
        if (encoding == compressor.encodingName() && response.body != null) {
            val decompressedBytes = compressor.decompress(response.body!!.bytes())
            val decompressedBody =
                ResponseBody.create(response.body!!.contentType(), decompressedBytes)

            return response.newBuilder()
                .removeHeader("Content-Encoding")
                .body(decompressedBody)
                .build()
        }
        return response
    }
}
