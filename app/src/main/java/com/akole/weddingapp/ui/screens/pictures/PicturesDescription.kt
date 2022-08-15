package com.akole.weddingapp.ui.screens.pictures

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R

@Composable
fun PicturesDescription(
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
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                 onEventHandler.invoke(PicturesViewModel.ViewEvent.AddPhotosClicked)
            },
            enabled = !isCollectionLoading && !isUploadingImagesLoading && isPhotoServiceEnabled,
            modifier = Modifier.padding(vertical = 5.dp)) {
            Text(
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
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
