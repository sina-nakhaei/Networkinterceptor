package ir.thesinaa.network_interceptor.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.thesinaa.networkinterceptor.errorhandlinginterceptor.ErrorHandlingInterceptor
import ir.thesinaa.networkinterceptor.headerinterceptor.HeaderInterceptor
import ir.thesinaa.networkinterceptor.httprequestinterceptor.HttpRequestLoggingInterceptor
import ir.thesinaa.networkinterceptor.networkconnectioninterceptor.NetworkConnectionInterceptor
import ir.thesinaa.networkinterceptor.performancemonitoringinterceptor.PerformanceMonitoringInterceptor
import ir.thesinaa.networkinterceptor.retryinterceptor.Config
import ir.thesinaa.networkinterceptor.retryinterceptor.RetryInterceptor
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(@ApplicationContext context: Context): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply { setLevel(HttpLoggingInterceptor.Level.BODY) },
            )
            .addInterceptor(HeaderInterceptor(
                {
                    mapOf(
                        "Authorization" to "Bearer your-dynamic-token",
                        "User-Agent" to "MyApp Android",
                        "Accept-Language" to "en-US"
                    )
                }
            ))
            .addInterceptor(ErrorHandlingInterceptor())
            .addInterceptor(HttpRequestLoggingInterceptor())
            .addInterceptor(RetryInterceptor(Config(3, 2000, emptyList())))
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(PerformanceMonitoringInterceptor())
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
        okhttpCallFactory: dagger.Lazy<Call.Factory>,
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl("https://httpbin.org/")
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }
}