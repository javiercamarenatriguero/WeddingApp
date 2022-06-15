package com.akole.weddingapp.domain.di

import com.akole.weddingapp.domain.repositories.ImagesRepository
import com.akole.weddingapp.domain.repositories.SongsRepository
import com.akole.weddingapp.domain.usecases.GetImages
import com.akole.weddingapp.domain.usecases.GetSongs
import com.akole.weddingapp.domain.usecases.SaveImages
import com.akole.weddingapp.domain.usecases.SaveSong
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDomainModule {

    @Provides
     fun providesGetImages(
        imagesRepository: ImagesRepository
     ): GetImages = GetImages(imagesRepository)

    @Provides
    fun providesSaveImages(
        imagesRepository: ImagesRepository
    ): SaveImages = SaveImages(imagesRepository)

    @Provides
    fun providesGetSongs(
        songsRepository: SongsRepository
    ): GetSongs = GetSongs(songsRepository)

    @Provides
    fun providesSaveSong(
        songsRepository: SongsRepository
    ): SaveSong = SaveSong(songsRepository)
}
