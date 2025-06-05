package com.example.chicagoart.domain.usecase

import com.example.chicagoart.data.repository.ItemRepository
import com.example.chicagoart.domain.model.Item
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(): List<Item> {
        return repository.fetchItems()
    }
}