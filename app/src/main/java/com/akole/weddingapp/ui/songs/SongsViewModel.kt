package com.akole.weddingapp.ui.songs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akole.weddingapp.data.models.Song
import com.akole.weddingapp.data.repositories.RepositoryImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SongsViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents: Flow<OneShotEvent> = _oneShotEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            syncSongList()
        }
    }

    private fun syncSongList() {
        updateState(isLoading = true)
        RepositoryImpl.getSongList( { documentSnapshot ->
            if (documentSnapshot != null && !documentSnapshot.isEmpty) {
                var songList: MutableList<Song> = mutableListOf()
                val documents = documentSnapshot.documents
                documents.forEach { document ->
                    document.toObject(Song::class.java)?.apply {
                        songList.add(this)
                    }
                }
                updateState(songList = songList)
            }
            updateState(isLoading = false)
        },{
            updateState(isLoading = false)
        }
        )
    }

    fun on(event: ViewEvent): Unit = with(event) {
        when (this) {
            is ViewEvent.SongTextChange -> {
                updateState(song = value)
            }
            is ViewEvent.ArtistTextChange -> {
                updateState(artist = value)
            }
            ViewEvent.AddClicked -> onAddClicked()
            ViewEvent.DialogClicked -> updateState(isDialogShown = false)
            ViewEvent.ArtistCompleted -> emit(OneShotEvent.HideKeyboard)
        }
    }

    private fun onAddClicked() {
        RepositoryImpl.saveSong(
            Song(
                name = state.song,
                artist = state.artist
            )
        )
        syncSongList()
        updateState(
            song = "",
            artist = "",
            isDialogShown = true
        )
        emit(OneShotEvent.HideKeyboard)
    }

    private fun emit(event: OneShotEvent) {
        viewModelScope.launch {
            _oneShotEvents.send(event)
        }
    }

    sealed interface OneShotEvent {
        object HideKeyboard : OneShotEvent
    }

    sealed interface ViewEvent {
        data class SongTextChange(val value: String) : ViewEvent
        data class ArtistTextChange(val value: String) : ViewEvent
        object AddClicked: ViewEvent
        object DialogClicked: ViewEvent
        object ArtistCompleted: ViewEvent
    }

    data class UiState(
        val song: String = "",
        val artist: String? = "",
        val songList: List<Song> = emptyList(),
        val isLoading: Boolean = false,
        val isButtonReady: Boolean = false,
        val isDialogShown: Boolean = false

    )

    private fun updateState(
        song: String = state.song,
        artist: String? = state.artist,
        songList: List<Song> = state.songList,
        isLoading: Boolean = state.isLoading,
        isButtonReady: Boolean = checkSubmitButton(song),
        isDialogShown: Boolean = state.isDialogShown
    ) {
        state = UiState(
            song,
            artist,
            songList,
            isLoading,
            isButtonReady,
            isDialogShown
        )
    }

    private fun checkSubmitButton(song: String) = !song.isNullOrEmpty()
}