package com.deanezra.chicagoart.domain.model

data class Artwork(
    val id: Int,
    val title: String,
    val date: String?,
    val artist: String?,
    val medium: String?,
    val type: String?,
    val imageId: String?,

    // TODO: Map to this in mapper
    val smallImageUrl: String?,
    val largeImageUrl: String?
)
