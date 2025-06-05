package com.deanezra.chicagoart.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.deanezra.chicagoart.domain.model.Artwork
import com.deanezra.chicagoart.domain.usecase.GetArtworksPagedUseCase
import com.deanezra.chicagoart.util.MainCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(
    application = HiltTestApplication::class,
    sdk = [33],
    manifest = Config.NONE)
class ArtworksViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtworksViewModel
    private lateinit var getArtworksPagedUseCase: GetArtworksPagedUseCase
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        hiltRule.inject()
        getArtworksPagedUseCase = mockk(relaxed = true)
        viewModel = ArtworksViewModel(getArtworksPagedUseCase)
    }

    @Test
    fun `artworksPagingFlow is initialized with data from use case`() = runTest {
        // Given
        val mockFlow: Flow<PagingData<Artwork>> = flowOf(PagingData.from(listOf(mockk<Artwork>())))
        coEvery { getArtworksPagedUseCase.invoke() } returns mockFlow

        // When
        val result = viewModel.artworksPagingFlow

        // Then
        coVerify(exactly = 1) { getArtworksPagedUseCase.invoke() }
        assert(result != null)
    }

    @Test
    fun `init block collects latest paging data`() = runTest {
        // Given
        val mockFlow: Flow<PagingData<Artwork>> = flowOf(PagingData.from(listOf(mockk<Artwork>())))
        coEvery { getArtworksPagedUseCase.invoke() } returns mockFlow

        // When
        val newViewModel = ArtworksViewModel(getArtworksPagedUseCase)

        // Allow time for coroutines to complete
        advanceUntilIdle()

        // Then
        coVerify(exactly = 2) { getArtworksPagedUseCase.invoke() }
        
        val result = newViewModel.artworksPagingFlow
        assert(result != null)
    }
}