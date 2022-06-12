package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.Constants.PHOTO_AVAILABLE_TIMESTAMP
import com.google.firebase.storage.ListResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class PicturesViewModel: ViewModel() {

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
        isLoading: Boolean = state.isUploadingImagesLoading,
        uploadingImages: Int = state.uploadingImages,
        uploadingProgress: Int = state.uploadingProgress,
        isCollectionLoading: Boolean = state.isCollectionLoading,
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
