package com.codingwithjks.tvapp.di

import android.app.Application
import androidx.room.Room
import com.codingwithjks.tvapp.adapter.RestaurantAdapter
import com.codingwithjks.tvapp.container.BaseApp
import com.codingwithjks.tvapp.data.dao.RestaurantDao
import com.codingwithjks.tvapp.data.database.RestaurantDatabase
import com.codingwithjks.tvapp.data.network.ApiService
import com.codingwithjks.tvapp.data.repository.DefaultRepository
import com.codingwithjks.tvapp.data.repository.RestaurantRepository
import com.codingwithjks.tvapp.ui.MainViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    factory { RestaurantAdapter() }
    single { ApiService() }
    single { providesDatabase(BaseApp()) }
    single { providesDao(get()) }
    factory { DefaultRepository(get(),get()) } bind RestaurantRepository::class
    viewModel { MainViewModel(get()) }
}

fun providesDatabase(application: Application) : RestaurantDatabase =
    Room.databaseBuilder(application, RestaurantDatabase::class.java,
        "restaurantDatabase")
        .build()

fun providesDao(db: RestaurantDatabase): RestaurantDao =
    db.getDao()