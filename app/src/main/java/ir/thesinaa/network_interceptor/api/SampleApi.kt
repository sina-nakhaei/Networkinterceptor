package ir.thesinaa.network_interceptor.api

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface SampleApi {
    @GET("json")
    suspend fun getSlideShow(): ApiResponse<SlideShowResponse>

    //test any kind of status code
    @GET("status/403")
    suspend fun getStatusCode(): ApiResponse<Unit>
}