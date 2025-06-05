package com.deanezra.chicagoart.domain.usecase

import com.deanezra.chicagoart.data.repository.ItemRepository
import com.deanezra.chicagoart.domain.model.Item
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(): List<Item> {
        return repository.fetchItems()
    }
}