package com.deanezra.chicagoart.domain.usecase

import androidx.paging.PagingData
import com.deanezra.chicagoart.data.repository.ArtworkRepository
import com.deanezra.chicagoart.domain.model.Artwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtworksPagedUseCase @Inject constructor(
    private val repository: ArtworkRepository
) {
    operator fun invoke(): Flow<PagingData<Artwork>> {
        return repository.getArtworksPaged().flow
    }
}