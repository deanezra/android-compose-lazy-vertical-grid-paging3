package com.example.chicagoart.data.repository

import com.example.chicagoart.data.mapper.toDomain
import com.example.chicagoart.data.remote.ApiService
import com.example.chicagoart.domain.model.Item
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ItemRepository {
    override suspend fun fetchItems(): List<Item> {
        return apiService.fetchItems().map {
            it.toDomain()
        }
    }
}