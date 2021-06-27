package com.codingwithjks.tvapp.container

import com.codingwithjks.tvapp.adapter.RestaurantAdapter
import com.codingwithjks.tvapp.ui.MainViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class BaseComponent : KoinComponent {

    val restaurantAdapter:RestaurantAdapter by inject()
    val mainViewModel:MainViewModel by inject()
}