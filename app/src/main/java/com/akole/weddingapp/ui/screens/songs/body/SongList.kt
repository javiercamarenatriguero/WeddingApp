package com.akole.weddingapp.ui.screens.songs.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
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
import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.ui.screens.songs.SongsScreenTestTags
import com.akole.weddingapp.ui.theme.ExtraLightGray

@Composable
internal fun SongList(
    songItemList: List<Song>,
    isLoading: Boolean
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SongItemListTitle()
        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier.testTag(SongsScreenTestTags.LOADING_VIEW)
            )
        } else {
            LazyColumn (
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .background(ExtraLightGray)
                    .testTag(SongsScreenTestTags.SONGS_COLUMN)
            ) {
                items(songItemList.size) { index ->
                    SongRow(songItem = songItemList[index])
                }
            }
        }
    }
}

@Composable
private fun SongItemListTitle() {
    Text(
        text = stringResource(id = R.string.song_list_title),
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
    Divider(
        color = Color.LightGray,
        modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp).height(2.dp)
    )
    Text(
        text = stringResource(id = R.string.song_list_order),
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}
