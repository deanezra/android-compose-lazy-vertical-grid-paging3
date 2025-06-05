package com.deanezra.chicagoart.domain.model

import com.google.gson.annotations.SerializedName

data class Artwork(
    val id: Int,
    val title: String,
    val artist: String,
    val date: String?,
    val medium: String?,
    val type: String?,
    val smallImageUrl: String,
    val largeImageUrl: String
)