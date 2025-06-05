package com.deanezra.chicagoart.data.repository

import com.deanezra.chicagoart.data.mapper.toDomain
import com.deanezra.chicagoart.data.remote.ApiService
import com.deanezra.chicagoart.domain.model.Artwork
import javax.inject.Inject

class ArtworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ArtworkRepository {
    companion object {
        const val ITEMS_PER_PAGE = 20
    }

    override suspend fun fetchArtworks(): List<Artwork?> {
        return apiService.fetchArtworks(ITEMS_PER_PAGE, 1).data.map {
            it.toDomain()
        }
    }
}