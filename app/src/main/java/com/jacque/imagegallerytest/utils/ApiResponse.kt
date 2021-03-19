package com.jacque.imagegallerytest.utils

/**
 * Use this class to set up responses from our coRoutine API calls
 */
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Failure<out T>(val errorMessage: String? = "", val throwable: Throwable? = null) :
        ApiResponse<T>()
}