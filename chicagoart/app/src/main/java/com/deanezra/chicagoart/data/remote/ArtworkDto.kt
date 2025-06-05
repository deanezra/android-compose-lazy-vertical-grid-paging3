package com.deanezra.chicagoart.data.remote

data class ArtworkDto(
    val id: Int,
    val title: String,
    val date_display: String?,
    val artist_display: String?,
    val medium_display: String?,
    val artwork_type_title: String?,
    val image_id: String?
)
