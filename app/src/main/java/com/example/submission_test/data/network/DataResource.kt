package com.example.submission_test.data.network

import okhttp3.ResponseBody


/**
 * Created by aldhykohar on 2/18/2022.
 */
sealed class DataResource<out T> {
    data class Success<out T>(val value: T) : DataResource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val otherMessage: String?
    ) : DataResource<Nothing>()

    object Loading : DataResource<Nothing>()
}