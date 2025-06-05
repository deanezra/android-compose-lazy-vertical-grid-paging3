package com.deanezra.chicagoart.data.remote

import com.google.gson.annotations.SerializedName

data class ArtworkDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("date_display")
    val date: String?,

    @SerializedName("artist_display")
    val artist: String?,

    @SerializedName("medium_display")
    val medium: String?,

    @SerializedName("artwork_type_title")
    val type: String?,

    @SerializedName("image_id")
    val imageId: String
)
