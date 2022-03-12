package com.akole.weddingapp.ui.songs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R
import com.akole.weddingapp.data.models.Song
import com.akole.weddingapp.ui.theme.DarkPink

@Composable
fun SongRow(
    position: Int,
    songItem: Song
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Text(
            text = position.toString(),
            fontSize = 30.sp,
            modifier = Modifier
                .padding(10.dp)
                .width(20.dp),
            textAlign = TextAlign.Center,
        )
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
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Cursive,
                    color = DarkPink,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if(songItem.artist.isNullOrEmpty()) stringResource(id = R.string.unknown_artist) else songItem.artist,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                    color = Color.DarkGray
                )
            }
            Divider(
                color = Color.LightGray,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}