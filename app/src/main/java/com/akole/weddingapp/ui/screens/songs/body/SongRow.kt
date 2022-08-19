package com.akole.weddingapp.ui.screens.songs.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akole.weddingapp.R
import com.akole.weddingapp.domain.models.Song
import com.akole.weddingapp.ui.theme.DarkPink

@Composable
internal fun SongRow(
    songItem: Song
) {
    Card(
        modifier = Modifier.padding(5.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.White)
            ) {
                Row {
                    Icon(imageVector = Icons.Default.MusicNote, contentDescription = "")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = songItem.name ?: "",
                        style = MaterialTheme.typography.h2,
                        color = DarkPink
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = songItem.artist ?: stringResource(id = R.string.unknown_artist),
                        style = MaterialTheme.typography.h2,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}