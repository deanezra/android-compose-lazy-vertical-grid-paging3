package com.deanezra.chicagoart.data.network

import android.graphics.pdf.LoadParams
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.deanezra.chicagoart.data.mapper.toArtwork
import com.deanezra.chicagoart.data.remote.ArtApiService
import com.deanezra.chicagoart.domain.model.Artwork

class ArtworksPagingSource(
    private val api: ArtApiService
) : PagingSource<Int, Artwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artwork> {
        return try {
            val page = params.key ?: 1
            Log.d("PagingSource", "Loading page $page")

            val response = api.getArtworks(page)
            Log.d("PagingSource", "Received ${response.data.size} artworks")

            val artworks = response.data.map { it.toArtwork() }

            val nextPage = if (response.pagination.next_url != null) page + 1 else null

            LoadResult.Page(
                data = artworks,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            Log.e("PagingSource", "Error loading artworks", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artwork>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
