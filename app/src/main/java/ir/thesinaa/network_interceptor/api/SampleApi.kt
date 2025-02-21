package ir.thesinaa.network_interceptor.api

import com.skydoves.sandwich.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SampleApi {
    @GET("json")
    suspend fun getSlideShow(): ApiResponse<SlideShowResponse>

    //test any kind of status code
    @GET("status/403")
    suspend fun getStatusCode(): ApiResponse<Unit>

    @POST("anything")
    suspend fun createUser(@Body user: User): ApiResponse<User>
}


@Serializable
data class User(
    @SerialName("id")
    val id: Int = 1,

    @SerialName("name")
    val name: String = "Sina"
)
