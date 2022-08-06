package com.akole.weddingapp.data.di

import com.akole.weddingapp.data.repositories.ImagesRepositoryImpl
import com.akole.weddingapp.data.repositories.SongsRepositoryImpl
import com.akole.weddingapp.domain.repositories.ImagesRepository
import com.akole.weddingapp.domain.repositories.SongsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
     fun providesImagesRepository(): ImagesRepository = ImagesRepositoryImpl()

    @Provides
    fun providesSongsRepository(): SongsRepository = SongsRepositoryImpl()
}
