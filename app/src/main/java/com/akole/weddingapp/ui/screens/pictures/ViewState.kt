package com.akole.weddingapp.ui.screens.pictures

import android.net.Uri

data class ViewState(
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