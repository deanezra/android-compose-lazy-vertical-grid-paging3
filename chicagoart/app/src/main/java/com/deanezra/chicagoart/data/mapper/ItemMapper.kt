package com.deanezra.chicagoart.data.mapper

import com.deanezra.chicagoart.data.remote.ItemDto
import com.deanezra.chicagoart.domain.model.Item

fun ItemDto.toDomain(): Item {
    return Item(value = this.value) // Example transformation
}