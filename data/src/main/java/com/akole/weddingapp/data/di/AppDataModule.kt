package com.akole.weddingapp.data.di

import com.akole.weddingapp.data.repositories.ImagesRepositoryImpl
import com.akole.weddingapp.data.repositories.SongsRepositoryImpl
import com.akole.weddingapp.domain.repositories.ImagesRepository
import com.akole.weddingapp.domain.repositories.SongsRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Singleton
    @Provides
     fun providesImagesRepository(): ImagesRepository = ImagesRepositoryImpl(
        storage = Firebase.storage
     )

    @Singleton
    @Provides
    fun providesSongsRepository(): SongsRepository = SongsRepositoryImpl(
        fireStore = Firebase.firestore
    )
}
