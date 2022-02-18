package com.example.submission_test.data.model.api


import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("status_code")
    var statusCode: Int? = null,
    @SerializedName("message")
    var statusMessage: String? = null,
    @SerializedName("success")
    var success: Boolean? = null
)