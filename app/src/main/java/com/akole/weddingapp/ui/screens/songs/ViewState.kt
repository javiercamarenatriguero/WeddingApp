package com.akole.weddingapp.ui.screens.songs

import com.akole.weddingapp.domain.models.Song

data class ViewState(
    val song: String = "",
    val artist: String? = "",
    val songList: List<Song> = emptyList(),
    val isLoading: Boolean = false,
    val isButtonReady: Boolean = false,
    val isDialogShown: Boolean = false
)