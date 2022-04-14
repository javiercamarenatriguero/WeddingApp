package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akole.weddingapp.ui.screens.home.HomeViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class PicturesViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    fun on(event: ViewEvent) = with(event) {
        when(this) {
            is ViewEvent.GetImagesResponse -> {
                updateState(isLoading = true)
                saveImages(value)

            }
        }
    }
    data class UiState(
        val isLoading: Boolean = false
    )

    private fun saveImages(list: List<@JvmSuppressWildcards Uri>) {
        ImagesRepositoryImpl.saveImages(
            list,
            onFailureListener = {
                updateState(isLoading = false)
            } ,
            onSuccessListener = {
                updateState(isLoading = false)
            }
        )
    }

    private fun updateState(
        isLoading: Boolean = state.isLoading
    ) {
        state = UiState(
            isLoading = isLoading
        )
    }

    sealed interface ViewEvent {
        class GetImagesResponse(val value: List<@JvmSuppressWildcards Uri>) : ViewEvent
    }
}