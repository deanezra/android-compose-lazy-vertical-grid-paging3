package com.example.chicagoart.data.mapper

import com.example.chicagoart.data.remote.ItemDto
import com.example.chicagoart.domain.model.Item

fun ItemDto.toDomain(): Item {
    return Item(value = this.value) // Example transformation
}