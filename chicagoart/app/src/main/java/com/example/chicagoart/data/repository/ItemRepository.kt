package com.example.chicagoart.data.repository

import com.example.chicagoart.domain.model.Item

interface ItemRepository {
    suspend fun fetchItems(): List<Item>
}