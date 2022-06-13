package com.akole.weddingapp.data.di

import ImagesRepositoryImpl
import com.akole.weddingapp.domain.ImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
     fun providesImagesRepository(): ImagesRepository = ImagesRepositoryImpl()
}
