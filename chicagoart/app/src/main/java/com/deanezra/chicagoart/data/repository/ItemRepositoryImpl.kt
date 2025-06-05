package com.deanezra.chicagoart.data.repository

import com.deanezra.chicagoart.data.mapper.toDomain
import com.deanezra.chicagoart.data.remote.ApiService
import com.deanezra.chicagoart.domain.model.Item
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