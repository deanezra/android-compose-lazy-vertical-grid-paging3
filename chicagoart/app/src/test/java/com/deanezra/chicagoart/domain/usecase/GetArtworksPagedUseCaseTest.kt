package com.deanezra.chicagoart.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import app.cash.turbine.test
import com.deanezra.chicagoart.data.repository.ArtworkRepository
import com.deanezra.chicagoart.domain.model.Artwork
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetArtworksPagedUseCaseTest {

    companion object {
        private val mockArtwork = Artwork(
            id = 1,
            title = "Mona Lisa",
            imageId = "test-image-id",
            date = "20-07-1503",
            artist = "Leonardo da Vinci",
            medium = "Oil on white poplar panel",
            type = "Painting",
            smallImageUrl = "https://example.com/image1-small.jpg",
            largeImageUrl = "https://example.com/image1-large.jpg"
        )
    }

    private lateinit var useCase: GetArtworksPagedUseCase
    private lateinit var mockRepository: ArtworkRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        useCase = GetArtworksPagedUseCase(mockRepository)
    }

    @Test
    fun `invoke calls repository getArtworksPaged`() = runTest {
        // Given some artwork and paging data
        val mockPager = mockk<Pager<Int, Artwork>>()
        val mockPagingData = PagingData.from(listOf(mockArtwork))
        every { mockRepository.getArtworksPaged() } returns mockPager
        every { mockPager.flow } returns flowOf(mockPagingData)

        // When usecase is invoked:
        useCase()

        // Then check that usecase calls the repository:
        verify { mockRepository.getArtworksPaged() }
    }

    @Test
    fun `invoke returns correct Flow of PagingData`() = runTest {
        // Given repo returns PagingData with mona lisa artwork:
        val mockPager = mockk<Pager<Int, Artwork>>()
        val mockPagingData = PagingData.from(listOf(mockArtwork))
        every { mockRepository.getArtworksPaged() } returns mockPager
        every { mockPager.flow } returns flowOf(mockPagingData)

        // When use case is invoked:
        val result = useCase()

        // Then the same mona lisa artwork packaged in a pagingData is emitted:
        result.test {
            val emittedPagingData = awaitItem()
            assertEquals(mockPagingData, emittedPagingData)
            awaitComplete()
        }
    }
}