package com.codingwithjks.tvapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithjks.tvapp.data.Restaurant
import com.codingwithjks.tvapp.data.repository.DefaultRepository
import com.codingwithjks.tvapp.data.repository.RestaurantRepository
import com.codingwithjks.tvapp.data.util.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val restaurantRepository: RestaurantRepository) : ViewModel() {

    init {
        getAllRestaurant()
    }
    private val _apiStateFlow:MutableStateFlow<State<List<Restaurant>>> = MutableStateFlow(State.Empty())
    val apiStateFlow:StateFlow<State<List<Restaurant>>> = _apiStateFlow

    private fun getAllRestaurant() = viewModelScope.launch {
        restaurantRepository.getAllRestaurant()
            .onStart {
                _apiStateFlow.value = State.loading()
            }.map { resource->
                 State.fromResource(resource)
            }.collect {response->
                _apiStateFlow.value = response
            }
    }
}