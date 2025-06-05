package com.deanezra.chicagoart.data.remote

data class PaginationDto(
    val total: Int,
    val limit: Int,
    val offset: Int,
    val total_pages: Int,
    val current_page: Int,
    val next_url: String?
)
