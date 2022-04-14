import android.net.Uri
import com.akole.weddingapp.data.repositories.ImagesRepository
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.File

object ImagesRepositoryImpl: ImagesRepository {
    private val storage = Firebase.storage
    private val storageRef = storage.reference

    override fun getImageList() {
        TODO("Not yet implemented")
    }

    override fun saveImages(
        list: List<Uri>,
        onFailureListener: OnFailureListener,
        onSuccessListener: OnSuccessListener<in UploadTask.TaskSnapshot>
    ) {
        list.forEach { uri ->
            val fileRef = storageRef.child("images/${uri.lastPathSegment?.substringAfterLast('/')}")

            var uploadTask = fileRef.putFile(uri)
            uploadTask
                .addOnFailureListener(onFailureListener)
                .addOnSuccessListener(onSuccessListener)
        }
    }

}