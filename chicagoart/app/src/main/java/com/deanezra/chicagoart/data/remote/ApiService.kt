package com.deanezra.chicagoart.data.remote

import retrofit2.Call
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


interface ApiService {

    @GET("artworks/{id}?fields=id,title,artist_display,date_display,medium_display,image_id,artwork_type_title")
    suspend fun fetchArtworkById(@Path("id") id: Int): ArtworksWrapperDto

    @GET("artworks?fields=id,title,image_id")
    suspend fun fetchArtworks(
        @Query("limit") itemsPerPage: Int,
        @Query("page") page: Int
    ): ArtworksWrapperDto

}