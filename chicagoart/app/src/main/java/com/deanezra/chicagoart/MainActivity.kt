package com.deanezra.chicagoart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.deanezra.chicagoart.domain.model.Artwork
import com.deanezra.chicagoart.ui.theme.ChicagoArtTheme
import com.deanezra.chicagoart.viewmodel.ArtworksViewModel
import android.util.Log

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val itemsViewModel: ArtworksViewModel = hiltViewModel()

            ChicagoArtTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtworksGridScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtworksGridScreen(viewModel: ArtworksViewModel = hiltViewModel()) {
    val artworks = viewModel.artworksPagingFlow.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(artworks.itemCount) { index ->
            val artwork = artworks[index]
            Log.d("ComposeGrid", "Binding item at index $index: $artwork")

            artwork?.let {
                ArtworkCard(it)
            }
        }

        artworks.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
                loadState.append is LoadState.Error -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text("Error loading more items", color = Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
fun ArtworkCard(artwork: Artwork) {
    val imageUrl = artwork.imageId?.let {
        "https://www.artic.edu/iiif/2/${it}/full/843,/0/default.jpg"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = artwork.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = artwork.title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChicagoArtTheme {
        Greeting(name = "Android")
    }
}*/