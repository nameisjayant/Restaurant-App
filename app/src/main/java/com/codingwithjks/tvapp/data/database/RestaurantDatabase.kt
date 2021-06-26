package com.codingwithjks.tvapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithjks.tvapp.data.Restaurant
import com.codingwithjks.tvapp.data.dao.RestaurantDao

@Database(entities = [Restaurant::class] , version = 1 , exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun getDao():RestaurantDao

}