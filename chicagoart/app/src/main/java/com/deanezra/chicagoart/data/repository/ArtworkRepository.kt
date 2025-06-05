package com.deanezra.chicagoart.data.repository

import androidx.paging.Pager
import com.deanezra.chicagoart.domain.model.Artwork

interface ArtworkRepository {
    fun getArtworksPaged(): Pager<Int, Artwork>
}