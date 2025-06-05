package com.deanezra.chicagoart.data.remote

data class ArtworksResponseDto(
    val pagination: PaginationDto,
    val data: List<ArtworkDto>
)
