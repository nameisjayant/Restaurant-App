package com.codingwithjks.tvapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class Restaurant(
    val name:String,
    val description:String,
    val logo:String
){
    @PrimaryKey
    var id:Int?=null
}
