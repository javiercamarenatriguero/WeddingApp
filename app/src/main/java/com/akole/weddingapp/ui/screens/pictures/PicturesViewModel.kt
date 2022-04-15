package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.ui.screens.home.HomeViewModel
import com.google.firebase.storage.ListResult
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PicturesViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        syncImages()
    }

    fun on(event: ViewEvent) = with(event) {
        when(this) {
            is ViewEvent.GetImagesResponse -> {
                updateState(
                    isLoading = true,
                    uploadingImages = value.size
                )
                saveImages(value)

            }
        }
    }
    data class UiState(
        val isLoading: Boolean = false,
        val isCollectionLoading: Boolean = false,
        val uploadingImages: Int = 0,
        val uploadingProgress: Int = 0,
        val imageUrlList: List<Uri> = emptyList()
    )

    private fun saveImages(list: List<@JvmSuppressWildcards Uri>) {
        ImagesRepositoryImpl.saveImages(
            list,
            onFailureListener = {
                updateState(isLoading = false)
            } ,
            onSuccessListener = {
                updateState(isLoading = false)
                syncImages()
            },
            onProgressListener = { progress ->
                updateState(uploadingProgress = progress)
            }
        )
    }

    private fun syncImages() {
        updateState(isCollectionLoading = true)
        ImagesRepositoryImpl.getImageList(
            onFailureListener = {
                updateState(isCollectionLoading = false)
            },
            onSuccessListener = { itemList ->
                viewModelScope.launch {
                    loadImages(itemList = itemList)
                }
            }
        )
    }

    private suspend fun loadImages(itemList: ListResult ) {
        val list = mutableListOf<Uri>()
        var index = 0
        itemList.items.asFlow()
            .flatMapMerge{
                callbackFlow<Uri> {
                    it.downloadUrl
                        .addOnCompleteListener {
                            index++
                            trySend(it.result)
                            close()
                        }
                        .addOnFailureListener {
                            index++
                            close()
                        }
                        .addOnCanceledListener {
                            index++
                            close()
                        }
                    awaitClose {
                        close()
                    }
                }.buffer(Channel.UNLIMITED)
            }
            .catch {
                index++
            }
            .collect { uri ->
                list.add(uri)
            }
        updateState(
            imageUrlList = list,
            isCollectionLoading = false
        )
    }

    private fun updateState(
        isLoading: Boolean = state.isLoading,
        uploadingImages: Int = state.uploadingImages,
        uploadingProgress: Int = state.uploadingProgress,
        isCollectionLoading: Boolean = state.isCollectionLoading,
        imageUrlList: List<Uri> = state.imageUrlList
    ) {
        state = UiState(
            isLoading = isLoading,
            uploadingImages = uploadingImages,
            uploadingProgress = uploadingProgress,
            isCollectionLoading = isCollectionLoading,
            imageUrlList = imageUrlList,
        )
    }

    sealed interface ViewEvent {
        class GetImagesResponse(val value: List<@JvmSuppressWildcards Uri>) : ViewEvent
    }
}