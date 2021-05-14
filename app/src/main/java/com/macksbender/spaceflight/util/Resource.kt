package com.macksbender.spaceflight.util

sealed class Resource<T>(var data: T? = null, var msg: String? = null) {
    class Success<T>(data: T): Resource<T>(data = data)
    class Error<T>(msg: String, data: T? = null): Resource<T>(data, msg)
    class Loading<T>(data: T? = null): Resource<T>(data)
}