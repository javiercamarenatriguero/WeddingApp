package com.akole.weddingapp.domain

import android.net.Uri
import com.akole.weddingapp.data.models.Song
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.internal.ChannelFlow

interface ImagesRepository {
    fun getImageList(
        onFailureListener: OnFailureListener,
        onSuccessListener: OnSuccessListener<in ListResult>
    )
    fun saveImages(
        list: List<Uri>,
        index: Int = 0,
        onFailureListener: OnFailureListener,
        onSuccessListener: OnSuccessListener<in UploadTask.TaskSnapshot>,
        onProgressListener: (progress: Int) -> Unit
    )

    fun getImages(): Flow<GetImagesResponse>
}