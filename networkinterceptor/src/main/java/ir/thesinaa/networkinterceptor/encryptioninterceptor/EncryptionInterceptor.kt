package ir.thesinaa.networkinterceptor.encryptioninterceptor

import ir.thesinaa.networkinterceptor.encryptioninterceptor.encryptor.AESEncryptor
import ir.thesinaa.networkinterceptor.encryptioninterceptor.encryptor.Encryptor
import okhttp3.*
import okio.Buffer

class EncryptionInterceptor(
    private val encryptor: Encryptor = AESEncryptor()
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val response = chain.proceed(encryptRequest(originalRequest))

        return decryptResponse(response)
    }

    private fun encryptRequest(request: Request): Request {
        val body = request.body ?: return request

        val buffer = Buffer()
        body.writeTo(buffer)
        val requestData = buffer.readByteArray()

        val encryptedData = encryptor.encrypt(requestData)

        val encryptedBody = RequestBody.create(body.contentType(), encryptedData)

        return request.newBuilder()
            .method(request.method, encryptedBody)
            .build()
    }

    private fun decryptResponse(response: Response): Response {
        val responseBody = response.body ?: return response
        val responseBytes = responseBody.bytes()

        val decryptedData = encryptor.decrypt(responseBytes)

        val decryptedBody = ResponseBody.create(responseBody.contentType(), decryptedData)

        return response.newBuilder()
            .body(decryptedBody)
            .build()
    }
}