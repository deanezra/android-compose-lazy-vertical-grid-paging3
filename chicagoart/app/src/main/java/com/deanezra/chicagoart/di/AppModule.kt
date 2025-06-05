package com.deanezra.chicagoart.di

import com.deanezra.chicagoart.data.network.UserAgentInterceptor
import com.deanezra.chicagoart.data.remote.ArtApiService
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ArtApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ArtApiService =
        retrofit.create(ArtApiService::class.java)

    @Singleton
    @Provides
    fun provideArtworkRepository(api: ArtApiService): ArtworkRepository {
        return ArtworkRepositoryImpl(api)
    }
}