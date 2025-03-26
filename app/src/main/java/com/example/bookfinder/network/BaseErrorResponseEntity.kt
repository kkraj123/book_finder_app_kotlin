package com.example.bookfinder.network

import com.google.gson.annotations.SerializedName

open class BaseErrorResponseEntity(
    @SerializedName("errorCode")
    open var errorCode: String = "",

    @SerializedName("errorMessage")
    var errorMessage: String = ""
)