package com.deanezra.chicagoart.data.remote

import com.google.gson.annotations.SerializedName

data class PaginationDto(
    @SerializedName("total")
    val total: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("next_url")
    val nextUrl: String?,
)
