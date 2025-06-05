package com.deanezra.chicagoart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanezra.chicagoart.domain.model.Artwork
import com.deanezra.chicagoart.domain.usecase.GetArtworksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworksViewModel @Inject constructor(
    private val getArtworksUseCase: GetArtworksUseCase
) : ViewModel() {

    init {
        fetchArtworks()
    }

    private val _artworkItems = MutableStateFlow<List<Artwork>>(emptyList())
    val artworkItems: StateFlow<List<Artwork>> = _artworkItems

    fun fetchArtworks() {
        viewModelScope.launch {
            _artworkItems.value = getArtworksUseCase()
        }
    }
}