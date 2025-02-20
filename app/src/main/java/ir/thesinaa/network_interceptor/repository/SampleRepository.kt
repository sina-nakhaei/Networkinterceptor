package ir.thesinaa.network_interceptor.repository

import ir.thesinaa.network_interceptor.api.SampleApi
import javax.inject.Inject

class SampleRepository @Inject constructor(
    private val api: SampleApi
) {
    suspend fun getSlideShow() = api.getSlideShow()

    suspend fun getStatusCode() = api.getStatusCode()
}