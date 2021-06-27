package com.codingwithjks.tvapp.data.repository

import kotlinx.coroutines.flow.*

abstract class NetworkBoundRepository<RESULT,REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {

        emit(Resource.Success(fetchFromLocal().first()))
        val apiResponse = fetchFromRemote()

        if(apiResponse != null){
            saveRemoteData(apiResponse)
        }else{
            emit(Resource.Failure(apiResponse.toString()))
        }

        emitAll(
            fetchFromLocal().map {
                Resource.Success(it)
            }
        )
    }.catch {e->
        e.printStackTrace()
        emit(Resource.Failure("can't connect to internet..."))
    }

    protected abstract suspend fun saveRemoteData(response:REQUEST)

    protected abstract fun fetchFromLocal():Flow<RESULT>

    protected abstract suspend fun fetchFromRemote():REQUEST

}