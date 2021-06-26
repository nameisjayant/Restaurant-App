package com.codingwithjks.tvapp.di

import com.codingwithjks.tvapp.data.adapter.RestaurantAdapter
import com.codingwithjks.tvapp.data.network.ApiService
import org.koin.dsl.module

val appModule = module {

    factory { RestaurantAdapter() }
    single { ApiService() }
}