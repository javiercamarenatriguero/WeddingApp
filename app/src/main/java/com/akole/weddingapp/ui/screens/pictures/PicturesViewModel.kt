package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.Constants.PHOTO_AVAILABLE_TIMESTAMP
import com.akole.weddingapp.domain.GetImagesResponse
import com.akole.weddingapp.domain.ImagesRepository
import com.akole.weddingapp.domain.SaveImagesResponse
import com.google.firebase.storage.ListResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository
): ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents: Flow<OneShotEvent> = _oneShotEvents.receiveAsFlow()

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
            is ViewEvent.ShowPictureDialog -> {
                updateState(
                    isShownPictureDialog = true,
                    pictureUri = value
                )
            }
            ViewEvent.DismissDialog -> updateState(
                isShownPictureDialog = false,
                pictureUri = null
            )
            ViewEvent.AddPhotosClicked -> onAddPhotosClicked()
        }
    }
    data class UiState(
        val isUploadingImagesLoading: Boolean = false,
        val isCollectionLoading: Boolean = false,
        val isCollectionError: Boolean = false,
        val uploadingImages: Int = 0,
        val uploadingProgress: Int = 0,
        val imageUrlList: List<Uri> = emptyList(),
        val isShownPictureDialog: Boolean = false,
        val pictureUri: Uri? = null,
        val isPhotoServiceEnabled: Boolean = false
    )

    sealed interface OneShotEvent {
        object GoToImageGallery : OneShotEvent
    }

    private fun emit(event: OneShotEvent) {
        viewModelScope.launch {
            _oneShotEvents.send(event)
        }
    }

    private fun onAddPhotosClicked() {
        emit(OneShotEvent.GoToImageGallery)
    }

    private fun saveImages(list: List<@JvmSuppressWildcards Uri>) {
        if (list.isEmpty()) {
            updateState(isLoading = false)
        } else {
            viewModelScope.launch {
                imagesRepository.saveImages(list).collect { response ->
                    when (response) {
                        is SaveImagesResponse.Loading -> {
                            updateState(uploadingProgress = response.position)
                        }
                        is SaveImagesResponse.Success -> {
                            updateState(isLoading = false)
                            syncImages()
                        }
                        is SaveImagesResponse.Error -> {
                            updateState(isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun syncImages() {
        viewModelScope.launch {
            imagesRepository.getImages().collect { response ->
                when (response) {
                    is GetImagesResponse.Loading -> {
                        updateState(
                            isCollectionLoading = true,
                            isCollectionError = false
                        )
                    }
                    is GetImagesResponse.Success -> {
                        updateState(
                            isCollectionLoading = false,
                            isCollectionError = false,
                            imageUrlList = response.images
                        )
                    }
                    is GetImagesResponse.Error -> {
                        updateState(
                            isCollectionLoading = false,
                            isCollectionError = true
                        )
                    }
                }
            }
        }
    }

    private fun updateState(
        isLoading: Boolean = state.isUploadingImagesLoading,
        uploadingImages: Int = state.uploadingImages,
        uploadingProgress: Int = state.uploadingProgress,
        isCollectionLoading: Boolean = state.isCollectionLoading,
        isCollectionError: Boolean = state.isCollectionError,
        imageUrlList: List<Uri> = state.imageUrlList,
        isShownPictureDialog: Boolean = state.isShownPictureDialog,
        pictureUri: Uri? = state.pictureUri,
        isPhotoServiceEnabled: Boolean = isPhotoServiceEnabled()
    ) {
        state = UiState(
            isUploadingImagesLoading = isLoading,
            uploadingImages = uploadingImages,
            uploadingProgress = uploadingProgress,
            isCollectionLoading = isCollectionLoading,
            isCollectionError = isCollectionError,
            imageUrlList = imageUrlList,
            isShownPictureDialog = isShownPictureDialog,
            pictureUri = pictureUri,
            isPhotoServiceEnabled = isPhotoServiceEnabled
        )
    }

    sealed interface ViewEvent {
        class GetImagesResponse(val value: List<@JvmSuppressWildcards Uri>) : ViewEvent
        class ShowPictureDialog(val value: Uri) : ViewEvent
        object AddPhotosClicked: ViewEvent
        object DismissDialog: ViewEvent
    }

    private fun isPhotoServiceEnabled(): Boolean {
        val time = PHOTO_AVAILABLE_TIMESTAMP - Date().time
        return time < 0
    }
}
