package com.codingwithjks.tvapp.data.util

import com.codingwithjks.tvapp.data.repository.Resource

sealed class State<T> {

    class Loading<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class Failure<T>(val msg: String) : State<T>()
    class Empty<T> : State<T>()

    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isFailure(): Boolean = this is Failure

    companion object {

       private fun <T> success(data: T) = Success(data)

        private fun <T> failure(msg: String) = Failure<T>(msg)

        fun <T> loading() = Loading<T>()

        fun <T> fromResource(resource: Resource<T>): State<T> = when (resource) {
            is Resource.Success -> success(resource.data)
            is Resource.Failure -> failure(resource.msg)
        }

    }

}
