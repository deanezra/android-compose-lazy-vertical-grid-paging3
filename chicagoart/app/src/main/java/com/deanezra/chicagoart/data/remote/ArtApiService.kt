package com.deanezra.chicagoart.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Notes on Chicaog art web api endpoints:

// Endpoint 1: List of artworks: https://api.artic.edu/api/v1/artworks

// Use the ?fields=<fieldname>,... to specify the fields you want returned in your API response. For example:
// https://api.artic.edu/api/v1/artworks?fields=id,title,artist_display,date_display,medium_display,image_id,artwork_type_title

// Endpoint 2: Details of a specific artwork: https://api.artic.edu/api/v1/artworks/<id>

// Use the ?fields=<fieldname>,... to specify the fields you want returned in your API response. For example:
// https://api.artic.edu/api/v1/artworks/100?fields=id,title,artist_display,date_display,medium_display,image_id,artwork_type_title

interface ArtApiService {

    companion object {
        const val BASE_URL = "https://api.artic.edu/api/v1/"
    }

    @GET("artworks/{id}?fields=id,title,artist_display,date_display,medium_display,image_id,artwork_type_title")
    suspend fun getArtworkById(@Path("id") id: Int): ArtworksResponseDto

    @GET("artworks")
    suspend fun getArtworks(
        @Query("page") page: Int,
        @Query("fields") fields: String = "id,title,image_id"
    ): ArtworksResponseDto

}