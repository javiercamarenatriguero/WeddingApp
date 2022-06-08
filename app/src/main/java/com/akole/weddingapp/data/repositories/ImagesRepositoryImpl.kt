import android.net.Uri
import com.akole.weddingapp.data.repositories.ImagesRepository
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage

object ImagesRepositoryImpl: ImagesRepository {
    private val storage = Firebase.storage
    private val storageRef = storage.reference

    override fun getImageList(
        onFailureListener: OnFailureListener,
        onSuccessListener: OnSuccessListener<in ListResult>
    ) {
        val listRef = storage.reference.child("images")

        listRef.listAll()
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override fun saveImages(
        list: List<Uri>,
        index: Int,
        onFailureListener: OnFailureListener,
        onSuccessListener: OnSuccessListener<in UploadTask.TaskSnapshot>,
        onProgressListener: (progress: Int) -> Unit
    ) {
        if (list.isNotEmpty()) {
            val uri = list[index]
            val fileRef = storageRef.child("images/${uri.lastPathSegment?.substringAfterLast('/')}")
            onProgressListener(index + 1)
            var uploadTask = fileRef.putFile(uri)
            uploadTask
                .addOnFailureListener(onFailureListener)
                .addOnSuccessListener {
                    if (index == list.size - 1) {
                        // Finish loading images
                        onSuccessListener.onSuccess(it)
                    } else {
                        // Upload next image
                        saveImages(list, index + 1, onFailureListener, onSuccessListener, onProgressListener)
                    }
                }
        }
    }
}