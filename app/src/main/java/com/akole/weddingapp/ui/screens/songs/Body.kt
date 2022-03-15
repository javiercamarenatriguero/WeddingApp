package com.akole.weddingapp.ui.screens.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Body(
    viewState: SongsViewModel.UiState,
    onSongValueChanged: (String) -> Unit = {},
    onArtistValueChanged: (String) -> Unit = {},
    onSubmitClicked: () -> Unit = {},
    onArtistCompleted: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 20000.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SongsDescription()
                    Spacer(modifier = Modifier.height(5.dp))
                    SongsForm(
                        songValue = viewState.song,
                        artistValue = viewState.artist,
                        onSongValueChanged = onSongValueChanged,
                        onArtistValueChanged = onArtistValueChanged,
                        buttonEnabled = viewState.isButtonReady,
                        onSubmitClicked = onSubmitClicked,
                        onArtistCompleted = onArtistCompleted
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SongList(
                        songItemList = viewState.songList,
                        isLoading = viewState.isLoading
                    )
                }
            }
        }
    }
}
