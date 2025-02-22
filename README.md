OkHttp Interceptors Module
Overview
This module provides a set of OkHttp interceptors for handling various network concerns such as logging, error handling, encryption, and retries. These interceptors improve debugging, security, and network reliability in Android applications.

Interceptors
1. NetworkConnectionInterceptor
Purpose: Prevents requests when there's no internet connection.
How it works: Checks network status before making a request. Throws NoConnectivityException if offline.
Usage:

``` val client = OkHttpClient.Builder()
    .addInterceptor(NetworkConnectionInterceptor(context))
    .build()
