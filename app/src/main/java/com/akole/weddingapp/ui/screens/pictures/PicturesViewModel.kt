package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.Constants.PHOTO_AVAILABLE_TIMESTAMP
import com.akole.weddingapp.domain.usecases.GetImagesResponse
import com.akole.weddingapp.domain.usecases.GetImages
import com.akole.weddingapp.domain.usecases.SaveImages
import com.akole.weddingapp.domain.usecases.SaveImagesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getImages: GetImages,
    private val saveImages: SaveImages

): ViewModel() {

    var state by mutableStateOf(ViewState())
        private set

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents: Flow<OneShotEvent> = _oneShotEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadImages()
        }
    }

    fun onViewEvent(event: ViewEvent) = with(event) {
        when(this) {
            is ViewEvent.GetImagesResponse -> {
                updateState(
                    isLoading = true,
                    uploadingImages = value.size
                )
                viewModelScope.launch {
                    uploadImages(value.map {it.toString()})
                }
            }
            is ViewEvent.ShowPictureDialog -> {
                updateState(
                    isShownPictureDialog = true,
                    pictureUri = Uri.parse(value)
                )
            }
            ViewEvent.DismissDialog -> updateState(
                isShownPictureDialog = false,
                pictureUri = null
            )
            ViewEvent.AddPhotosClicked -> onAddPhotosClicked()
        }
    }

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

    suspend fun uploadImages(list: List<String>) {
        if (list.isEmpty()) {
            updateState(isLoading = false)
        } else {
            saveImages(list).collectLatest { response ->
                when (response) {
                    is SaveImagesResponse.Loading -> {
                        updateState(uploadingProgress = response.position)
                    }
                    is SaveImagesResponse.Success -> {
                        updateState(isLoading = false)
                        loadImages()
                    }
                    is SaveImagesResponse.Error -> {
                        updateState(isLoading = false)
                    }
                }
            }
        }
    }

    suspend fun loadImages() {
        getImages().collectLatest { response ->
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

    private fun updateState(
        isLoading: Boolean = state.isUploadingImagesLoading,
        uploadingImages: Int = state.uploadingImages,
        uploadingProgress: Int = state.uploadingProgress,
        isCollectionLoading: Boolean = state.isCollectionLoading,
        isCollectionError: Boolean = state.isCollectionError,
        imageUrlList: List<String> = state.imageUrlList,
        isShownPictureDialog: Boolean = state.isShownPictureDialog,
        pictureUri: Uri? = state.pictureUri,
        isPhotoServiceEnabled: Boolean = isPhotoServiceEnabled()
    ) {
        state = ViewState(
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
        class ShowPictureDialog(val value: String) : ViewEvent
        object AddPhotosClicked: ViewEvent
        object DismissDialog: ViewEvent
    }

    private fun isPhotoServiceEnabled(): Boolean {
        val time = PHOTO_AVAILABLE_TIMESTAMP - Date().time
        return time < 0
    }
}
