package com.deanezra.chicagoart.data.repository

import com.deanezra.chicagoart.domain.model.Artwork

interface ArtworkRepository {
    suspend fun fetchArtworks(): List<Artwork?>
}