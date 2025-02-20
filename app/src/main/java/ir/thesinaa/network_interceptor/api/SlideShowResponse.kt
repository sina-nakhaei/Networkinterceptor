package ir.thesinaa.network_interceptor.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SlideShowResponse(
    @SerialName("slideshow")
    val slideShow: SlideShow
)

@Serializable
data class SlideShow(
    val author: String,
    val title: String,
    val date: String
)