package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.theme.DarkPink

@Composable
fun SongsForm(
    songValue: String = "",
    artistValue: String = "",
    onSongValueChanged: (String) -> Unit = {},
    onArtistValueChanged: (String) -> Unit = {},
    onSubmitClicked: () -> Unit = {}
) {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .border(2.dp, DarkPink, RoundedCornerShape(5.dp)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        CustomOutlinedTextField(
            value = songValue,
            label = stringResource(id = R.string.songs_textfield_song_label),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { song ->
                onSongValueChanged(song)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomOutlinedTextField(
            value = artistValue,
            label = stringResource(id = R.string.songs_textfield_artist_label),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { artist ->
                onArtistValueChanged(artist)
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = onSubmitClicked,
            modifier = Modifier.padding(vertical = 5.dp)) {
            Text(text = stringResource(id = R.string.submit_song))
        }
    }
}