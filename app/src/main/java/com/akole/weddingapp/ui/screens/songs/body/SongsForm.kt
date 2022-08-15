package com.akole.weddingapp.ui.screens.songs.body

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akole.weddingapp.R
import com.akole.weddingapp.ui.common.CustomOutlinedTextField
import com.akole.weddingapp.ui.screens.songs.SongsScreenTestTags
import com.akole.weddingapp.ui.screens.songs.SongsViewModel
import com.akole.weddingapp.ui.theme.DarkPink
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SongsForm(
    songValue: String = "",
    artistValue: String? = "",
    buttonEnabled: Boolean = false,
    onEventHandler: (SongsViewModel.ViewEvent) -> Unit
    ) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    Column (
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .border(2.dp, DarkPink, RoundedCornerShape(5.dp))
            .bringIntoViewRequester(bringIntoViewRequester),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        CustomOutlinedTextField(
            value = songValue,
            label = stringResource(id = R.string.songs_textfield_song_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .testTag(SongsScreenTestTags.SONG_TEXT_FIELD),
            onValueChange = { song ->
                onEventHandler.invoke(SongsViewModel.ViewEvent.SongTextChange(song))
            },
            focusRequester = focusRequester,
            onFocusEvent = { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
            onKeyboardNext = {
                focusManager.moveFocus(FocusDirection.Down)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = ""
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomOutlinedTextField(
            value = artistValue ?: "",
            label = stringResource(id = R.string.songs_textfield_artist_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .testTag(SongsScreenTestTags.ARTIST_TEXT_FIELD),
            onValueChange = { artist ->
                onEventHandler.invoke(SongsViewModel.ViewEvent.ArtistTextChange(artist))
            },
            focusRequester = focusRequester,
            onFocusEvent = { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
            imeAction = ImeAction.Done,
            onKeyboardDone = {
                onEventHandler.invoke(SongsViewModel.ViewEvent.ArtistCompleted)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = ""
                )
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                 onEventHandler.invoke(SongsViewModel.ViewEvent.AddClicked)
            },
            enabled = buttonEnabled,
            modifier = Modifier.padding(vertical = 5.dp)) {
            Text(
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                text = stringResource(id = R.string.submit_song)
            )
        }
    }
}
