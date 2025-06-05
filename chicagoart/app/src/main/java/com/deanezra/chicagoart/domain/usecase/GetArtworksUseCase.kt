package com.deanezra.chicagoart.domain.usecase

import com.deanezra.chicagoart.data.repository.ArtworkRepository
import com.deanezra.chicagoart.domain.model.Artwork
import javax.inject.Inject

class GetArtworksUseCase @Inject constructor(
    private val repository: ArtworkRepository
) {
    suspend operator fun invoke(): List<Artwork?> {
        return repository.fetchArtworks()
    }
}