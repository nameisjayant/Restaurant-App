package com.codingwithjks.tvapp.data.repository

sealed class Resource<T>{

    class Success<T>(val data:T) : Resource<T>()
    class Failure<T>(val msg:String): Resource<T>()
}
