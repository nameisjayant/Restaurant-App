package com.codingwithjks.tvapp.data.repository

import com.codingwithjks.tvapp.data.Restaurant
import com.codingwithjks.tvapp.data.dao.RestaurantDao
import com.codingwithjks.tvapp.data.network.ApiService
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository{
    fun getAllRestaurant() : Flow<Resource<List<Restaurant>>>
}

class DefaultRepository(
    private val apiService: ApiService,
    private val dao: RestaurantDao
) : RestaurantRepository{

    override fun getAllRestaurant(): Flow<Resource<List<Restaurant>>>{
       return object : NetworkBoundRepository<List<Restaurant>,List<Restaurant>>(){
           override suspend fun saveRemoteData(response: List<Restaurant>) {
               dao.insert(response)
           }

           override fun fetchFromLocal(): Flow<List<Restaurant>> = dao.getAllResturant()

           override suspend fun fetchFromRemote(): List<Restaurant> = apiService.getRestaurant()

       } .asFlow()
    }
}