package com.deanezra.chicagoart.data.repository

import com.deanezra.chicagoart.domain.model.Item

interface ItemRepository {
    suspend fun fetchItems(): List<Item>
}