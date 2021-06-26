package com.codingwithjks.tvapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithjks.tvapp.data.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantList: List<Restaurant>)

    @Query("SELECT * FROM restaurant")
    fun getAllResturant():Flow<List<Restaurant>>

}