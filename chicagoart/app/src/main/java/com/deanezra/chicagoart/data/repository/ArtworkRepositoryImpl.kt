package com.deanezra.chicagoart.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.deanezra.chicagoart.data.network.ArtworksPagingSource
import com.deanezra.chicagoart.data.remote.ArtApiService
import com.deanezra.chicagoart.domain.model.Artwork
import javax.inject.Inject

class ArtworkRepositoryImpl @Inject constructor(
    private val api: ArtApiService
) : ArtworkRepository {

    override fun getArtworksPaged(): Pager<Int, Artwork> {
        return Pager(
            config = PagingConfig(pageSize = 12),
            pagingSourceFactory = { ArtworksPagingSource(api) }
        )
    }
}