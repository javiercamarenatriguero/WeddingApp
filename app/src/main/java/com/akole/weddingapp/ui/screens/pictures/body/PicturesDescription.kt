package com.akole.weddingapp.ui.screens.pictures.body

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.screens.pictures.PictureScreenTestTags
import com.akole.weddingapp.ui.screens.pictures.PicturesViewModel

@Composable
internal fun PicturesDescription(
    isUploadingImagesLoading: Boolean = false,
    isCollectionLoading: Boolean = false,
    isPhotoServiceEnabled: Boolean = false,
    numImages: Int = 0,
    progress: Int = 0,
    onEventHandler: (PicturesViewModel.ViewEvent) -> Unit,
) {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(R.string.pictures_description_text),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                 onEventHandler.invoke(PicturesViewModel.ViewEvent.AddPhotosClicked)
            },
            enabled = !isCollectionLoading && !isUploadingImagesLoading && isPhotoServiceEnabled,
            modifier = Modifier.padding(vertical = 5.dp)) {
            Text(
                text = stringResource(id = R.string.submit_photo)
            )
        }

        if (isUploadingImagesLoading) {
            LinearProgressIndicator(
                backgroundColor = Color.LightGray,
                color = Color.Blue,
                modifier = Modifier.testTag(PictureScreenTestTags.UPLOADING_PHOTO_PROGRESS)
            )
            Text(text = stringResource(id = R.string.pictures_uploading_progress_text) + " $progress / $numImages")
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
