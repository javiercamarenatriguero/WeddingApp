package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.ui.screens.home.HomeViewModel
import com.google.firebase.storage.ListResult
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
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
                updateState(isLoading = true)
                saveImages(value)

            }
        }
    }
    data class UiState(
        val isLoading: Boolean = false,
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
            }
        )
    }

    private fun syncImages() {
        updateState(isLoading = true)
        ImagesRepositoryImpl.getImageList(
            onFailureListener = {
                updateState(isLoading = false)
            },
            onSuccessListener = { itemList ->
                updateState(isLoading = false)
                viewModelScope.launch {
                    loadImages(itemList = itemList)
                }
            }
        )
    }

    private suspend fun loadImages(itemList: ListResult ) {
        val list = mutableListOf<Uri>()
        itemList.items.asFlow()
            .flatMapMerge {
                callbackFlow<Uri> {
                    it.downloadUrl.addOnCompleteListener {
                        trySend(it.result)
                    }
                    awaitClose { }
                }
            }
            .collect { uri ->
                list.add(uri)
                if(list.size == itemList.items.size) {
                    updateState(imageUrlList = list)
                }
            }
    }

    private fun updateState(
        isLoading: Boolean = state.isLoading,
        imageUrlList: List<Uri> = state.imageUrlList
    ) {
        state = UiState(
            isLoading = isLoading,
            imageUrlList = imageUrlList
        )
    }

    sealed interface ViewEvent {
        class GetImagesResponse(val value: List<@JvmSuppressWildcards Uri>) : ViewEvent
    }
}