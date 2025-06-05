package com.deanezra.chicagoart.di

import com.deanezra.chicagoart.data.network.UserAgentInterceptor
import com.deanezra.chicagoart.data.remote.ApiService
import com.deanezra.chicagoart.data.repository.ArtworkRepository
import com.deanezra.chicagoart.data.repository.ArtworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://api.artic.edu/api/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(userAgentInterceptor: UserAgentInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            // Web api requires a special user agent in every call identifying
            // project name and contact email, so we add header via interceptor:
            .addInterceptor(userAgentInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String, okHttpClient: OkHttpClient): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideItemRepository(apiService: ApiService): ArtworkRepository =
        ArtworkRepositoryImpl(apiService)
}