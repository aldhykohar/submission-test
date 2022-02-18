package com.example.submission_test.utils

import android.app.Activity
import com.example.submission_test.R
import com.example.submission_test.data.model.api.ErrorResponse
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.utils.UtilConstants.OTHER_ERROR
import com.example.submission_test.utils.UtilExtensions.showSnackBar
import com.example.submission_test.utils.UtilFunctions.loge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


/**
 * Created by aldhykohar on 2/18/2022.
 */
object UtilExceptions {
    class NoInternetException(message: String) : IOException(message)

    fun Activity.handleApiError(
        failure: DataResource.Failure,
        retry: (() -> Unit)? = null
    ) {
        loge("NoInternetException : $failure")
        if (failure.isNetworkError) {
            if (failure.errorCode == OTHER_ERROR) window.decorView.rootView.showSnackBar(
                failure.otherMessage.toString(),
                retry
            )
            else window.decorView.rootView.showSnackBar(getString(R.string.no_internet), retry)
        } else {
            try {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse? =
                    gson.fromJson(failure.errorBody?.charStream(), type)
                /*if (failure.errorCode == 401) {
                    if (this is MainActivity) window.decorView.rootView.showSnackBar(errorResponse?.statusMessage ?: getString(R.string.fetch_failed), retry)
                    else window.decorView.rootView.showSnackBar(errorResponse?.statusMessage ?: getString(R.string.fetch_failed), retry)
                } else window.decorView.rootView.showSnackBar(errorResponse?.statusMessage ?: getString(R.string.some_error))*/
                window.decorView.rootView.showSnackBar(
                    errorResponse?.statusMessage ?: getString(R.string.some_error)
                )
                loge("ErrorResponse NoInternetException: $errorResponse")
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}