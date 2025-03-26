package com.example.bookfinder.network

import android.util.Log
import com.google.gson.Gson
import retrofit2.Response


suspend fun <T> Response<T>.handleResponse(doActionOnSuccess: suspend (body: T) -> Unit = {}): NetworkResult<T> {
    return if (isSuccessful) {
        if (body() != null) {
            doActionOnSuccess.invoke(body()!!)
            NetworkResult.Success(body()!!)
        } else {
            NetworkResult.Error(message())
        }
    } else if (code() in listOf(401, 403)) {
        Log.e("ResponseHandler", "Response code: " + code())
        NetworkResult.Error(message(), code())
    } else {
        val genericErrorMessage = "Error encountered. Please try again later."
        val errorBody = errorBody()
        return try {
            val errorModel = Gson().fromJson(errorBody?.charStream(), BaseErrorResponseEntity::class.java)
            NetworkResult.Error(errorModel.errorMessage)
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.Error(genericErrorMessage)
        }
    }
}


