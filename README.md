# Network Interceptors Module

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Platform](https://img.shields.io/badge/platform-Android-brightgreen)
![Kotlin](https://img.shields.io/badge/language-Kotlin-purple)
![OkHttp](https://img.shields.io/badge/library-OkHttp-orange)
![JitPack](https://img.shields.io/badge/dependency-JitPack-yellow)

This module provides a set of custom `Interceptor` classes to enhance your network requests. It includes interceptors for compression, encryption, error handling, logging, header management, network connection checks, performance monitoring, retry logic, and more. You can easily integrate these into your network stack to improve performance, security, and reliability.

## Interceptors Overview 🚀

- **CompressionInterceptor**: Compresses requests and decompresses responses based on size thresholds.
- **EncryptionInterceptor**: Encrypts request data and decrypts response data.
- **ErrorHandlingInterceptor**: Handles client and server errors by throwing appropriate exceptions.
- **HeaderInterceptor**: Adds dynamic headers to every request based on a provided header map.
- **HttpRequestLoggingInterceptor**: Logs every HTTP request for debugging purposes.
- **NetworkConnectionInterceptor**: Ensures an active internet connection before proceeding with the request.
- **PerformanceMonitoringInterceptor**: Logs request performance metrics such as duration, request/response size.
- **RetryInterceptor**: Retries a failed request up to a specified number of attempts with delays.

## Usage
   ``````kotlin
     val client = OkHttpClient.Builder()
       .addInterceptor(CompressionInterceptor(compressor = MyCustomCompressor())) // Compress request and decompress response
       .addInterceptor(EncryptionInterceptor(encryptor = MyCustomEncryptor())) // Encrypt request and decrypt response
       .addInterceptor(ErrorHandlingInterceptor()) // Handle client and server errors
       .addInterceptor(HeaderInterceptor { mapOf("Authorization" to "Bearer token") }) // Add custom headers
       .addInterceptor(HttpRequestLoggingInterceptor()) // Log HTTP requests
       .addInterceptor(NetworkConnectionInterceptor(context)) // Check for internet connectivity
       .addInterceptor(PerformanceMonitoringInterceptor()) // Monitor request performance
       .addInterceptor(RetryInterceptor(Config(maxRetryAttempts = 3, delayMillis = 1000))) // Retry failed requests
       .build()
``````

## Installation 📦

To use this library in your project, follow these steps:

### Step 1: Add the JitPack repository

In your root `settings.gradle` file, add the JitPack repository:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the dependency

In your `build.gradle` (Module level), add the dependency:

```gradle
dependencies {
     implementation 'com.github.sina-nakhaei:Networkinterceptor:$version'
}
```


## Custom Encryption & Compression 💡

This library is designed to be flexible. You can define your own encryption and compression methods by implementing the respective interfaces:

🔹 **Custom Encryption**: Implement the `Encryptor` interface to use your own encryption logic.  
🔹 **Custom Compression**: Implement the `Compressor` interface to define a custom compression strategy.  

### Example:

```kotlin
class MyCustomEncryptor : Encryptor {
    override fun encrypt(data: ByteArray): ByteArray {
        // Your encryption logic here
    }

    override fun decrypt(data: ByteArray): ByteArray {
        // Your decryption logic here
    }
}
```
