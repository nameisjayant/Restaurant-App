package com.codingwithjks.tvapp.data.database

import android.app.Application
import androidx.room.Room
import com.codingwithjks.tvapp.data.dao.RestaurantDao


fun providesDatabase(application: Application) : RestaurantDatabase =
    Room.databaseBuilder(application,RestaurantDatabase::class.java,
    "restaurantDatabase")
        .build()

fun providesDao(db:RestaurantDatabase):RestaurantDao =
    db.getDao()