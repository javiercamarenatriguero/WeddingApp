package com.akole.weddingapp.data.repositories

import android.net.Uri
import com.akole.weddingapp.data.models.Song
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.UploadTask

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
}