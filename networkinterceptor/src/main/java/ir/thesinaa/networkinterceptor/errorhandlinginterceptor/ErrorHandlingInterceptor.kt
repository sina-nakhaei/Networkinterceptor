package ir.thesinaa.networkinterceptor.errorhandlinginterceptor

import ir.thesinaa.networkinterceptor.StatusCode
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorHandlingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        
        when (response.code) {
            in 400..499 -> handleClientErrors(response)
            in 500..599 -> handleServerErrors(response)
        }

        return response
    }

    private fun handleClientErrors(response: Response) {
        when (response.code) {
            StatusCode.BadRequest.code -> throw BadRequestException()
            StatusCode.Unauthorized.code -> throw UnauthorizedException()
            StatusCode.Forbidden.code -> throw ForbiddenException()
            StatusCode.NotFound.code -> throw NotFoundException()
            StatusCode.RequestTimeout.code -> throw RequestTimeoutException()
            StatusCode.TooManyRequests.code -> throw RateLimitExceededException()
            else -> throw ClientErrorException(response.code)
        }
    }

    private fun handleServerErrors(response: Response) {
        when (response.code) {
            StatusCode.InternalServerError.code -> throw InternalServerErrorException()
            StatusCode.BadGateway.code -> throw BadGatewayException()
            StatusCode.GatewayTimeout.code -> throw GatewayTimeoutException()
            StatusCode.ServiceUnavailable.code -> throw ServiceUnavailableException()
            else -> throw ServerErrorException(response.code)
        }
    }
}

class BadRequestException : IOException() {
    override val message: String
        get() = "Bad Request (400)"
}

class UnauthorizedException : IOException() {
    override val message: String
        get() = "Unauthorized (401)"
}

class ForbiddenException : IOException() {
    override val message: String
        get() = "Forbidden (403)"
}

class NotFoundException : IOException() {
    override val message: String
        get() = "Not Found (404)"
}

class RequestTimeoutException : IOException() {
    override val message: String
        get() = "Request Timeout (408)"
}

class InternalServerErrorException : IOException() {
    override val message: String
        get() = "Internal Server Error (500)"
}

class BadGatewayException : IOException() {
    override val message: String
        get() = "Bad Gateway (502)"
}

class GatewayTimeoutException : IOException() {
    override val message: String
        get() = "Gateway Timeout (504)"
}

class RateLimitExceededException : IOException() {
    override val message: String
        get() = "Rate Limit Exceeded (429)"
}

class ServiceUnavailableException : IOException() {
    override val message: String
        get() = "Service Unavailable (503)"
}

class ClientErrorException(private val statusCode: Int) : IOException() {
    override val message: String
        get() = "Client Error ($statusCode)"
}

class ServerErrorException(private val statusCode: Int) : IOException() {
    override val message: String
        get() = "Server Error ($statusCode)"
}