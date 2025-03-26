package com.example.bookfinder.network

/**
 * A generic class that contains data and status about loading this data.
 */
sealed class NetworkResult<out T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = -1
) {
//    object Loading: NetworkResult<Nothing>()
    object Empty : NetworkResult<Nothing>()
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Loading<T>(data: T? = null) : NetworkResult<T>(data)
    class Error<T>(message: String, code: Int? = -1, data: T? = null) :
        NetworkResult<T>(data, message, code)

}

//sealed class ApiState {
//    class Success(val data: List<TokenResponse>) : ApiState()
//    class Failure(val msg: Throwable) : ApiState()
//    object Loading : ApiState()
//    object Empty : ApiState()
//
//}

sealed class ResponseState<out T>{
    object Empty : ResponseState<Nothing>()
    object Loading : ResponseState<Nothing>()
    class Error(val throwable: Throwable) : ResponseState<Nothing>()
    class Success<T>(val item: T) : ResponseState<T>()
}