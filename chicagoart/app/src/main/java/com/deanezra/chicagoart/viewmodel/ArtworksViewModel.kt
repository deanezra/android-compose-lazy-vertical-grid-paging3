package com.deanezra.chicagoart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.deanezra.chicagoart.domain.model.Artwork
import com.deanezra.chicagoart.domain.usecase.GetArtworksPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworksViewModel @Inject constructor(
    private val getArtworksPagedUseCase: GetArtworksPagedUseCase
) : ViewModel() {

    val artworksPagingFlow: Flow<PagingData<Artwork>> = getArtworksPagedUseCase().cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            artworksPagingFlow.collectLatest {
                Log.d("ArtworksViewModel", "New PagingData received")
            }
        }
    }
}