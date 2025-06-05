package com.deanezra.chicagoart.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("items")
    suspend fun fetchItems(): List<ItemDto>
}