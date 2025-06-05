package com.deanezra.chicagoart.data.remote

import com.google.gson.annotations.SerializedName

data class ArtworksWrapperDto(

    // Pagination is only present when more than one artwork returned
    @SerializedName("pagination")
    val pagination: PaginationDto?,

    @SerializedName("data")
    val data: List<ArtworkDto>,
)
