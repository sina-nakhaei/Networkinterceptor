# Network Interceptors Module

This module provides a set of custom `Interceptor` classes to enhance your network requests. It includes interceptors for compression, encryption, error handling, logging, header management, network connection checks, performance monitoring, retry logic, and more. You can easily integrate these into your network stack to improve performance, security, and reliability.

## Interceptors Overview

- **CompressionInterceptor**: Compresses requests and decompresses responses based on size thresholds.
- **EncryptionInterceptor**: Encrypts request data and decrypts response data.
- **ErrorHandlingInterceptor**: Handles client and server errors by throwing appropriate exceptions.
- **HeaderInterceptor**: Adds dynamic headers to every request based on a provided header map.
- **HttpRequestLoggingInterceptor**: Logs every HTTP request for debugging purposes.
- **NetworkConnectionInterceptor**: Ensures an active internet connection before proceeding with the request.
- **PerformanceMonitoringInterceptor**: Logs request performance metrics such as duration, request/response size.
- **RetryInterceptor**: Retries a failed request up to a specified number of attempts with delays.

## Usage

### 1. **CompressionInterceptor**
   Compresses request bodies and decompresses responses when necessary.

   ```kotlin
   val compressionInterceptor = CompressionInterceptor()
   // Add to OkHttpClient
