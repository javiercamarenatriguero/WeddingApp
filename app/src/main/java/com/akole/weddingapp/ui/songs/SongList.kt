package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R
import com.akole.weddingapp.data.models.Song

@Composable
fun SongList(
    songItemList: List<Song>,
    isLoading: Boolean
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SongItemListTitle()
        if (isLoading){
            CircularProgressIndicator()
        } else {
            LazyColumn (
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 5.dp)
            ) {
                itemsIndexed(songItemList) { index, songItem ->
                    SongRow(songItem = songItem, position = index + 1)
                }
            }
        }
    }
}

@Composable
private fun SongItemListTitle() {
    Text(
        text = stringResource(id = R.string.song_list_title),
        modifier = Modifier.padding(horizontal = 20.dp),
        fontFamily = FontFamily.Cursive,
        fontSize = 26.sp
    )
    Divider(
        color = Color.LightGray,
        modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp).height(2.dp)
    )
    Text(
        text = stringResource(id = R.string.song_list_order),
        modifier = Modifier.padding(horizontal = 20.dp),
        fontFamily = FontFamily.Cursive,
        fontSize = 18.sp
    )
}