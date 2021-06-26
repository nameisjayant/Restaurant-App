package com.codingwithjks.tvapp.data.network

import com.codingwithjks.tvapp.data.Restaurant
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class ApiService {

    private val client = HttpClient(Android){

        install(DefaultRequest){
            headers.append("Content-Type","application/json")
        }
        install(JsonFeature){
            serializer = GsonSerializer()
        }

        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
    }

    suspend fun getRestaurant():List<Restaurant>{
      return  client.get {
            url("https://random-data-api.com/api/restaurant/random_restaurant?size=20")
        }
    }
}